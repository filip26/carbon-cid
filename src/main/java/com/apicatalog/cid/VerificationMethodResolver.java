package com.apicatalog.cid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import com.apicatalog.cid.document.IdentifierDocument;
import com.apicatalog.cid.document.VerificationMethod;

/**
 * Resolves a {@link VerificationMethod} referenced from an
 * {@link IdentifierDocument}, optionally resolving the controller document from
 * a {@link URI}.
 *
 * <p>
 * Supported verification relationship IRIs:
 * </p>
 * <ul>
 * <li><code>https://w3id.org/security#authentication</code></li>
 * <li><code>https://w3id.org/security#assertionMethod</code></li>
 * <li><code>https://w3id.org/security#keyAgreementMethod</code></li>
 * <li><code>https://w3id.org/security#capabilityInvocationMethod</code></li>
 * <li><code>https://w3id.org/security#capabilityDelegationMethod</code></li>
 * </ul>
 *
 * <p>
 * Resolution checks both existence and consistency: a method must be present in
 * the specified relation and its {@code controller} must match the document
 * {@code id}.
 * </p>
 */
public class VerificationMethodResolver {

    /** Supported verification relationships → document accessors. */
    protected final static Map<URI, Function<IdentifierDocument, Set<VerificationMethod>>> RELS;

    static {
        RELS = new HashMap<>();
        RELS.put(URI.create("https://w3id.org/security#authentication"),
                IdentifierDocument::authentication);
        RELS.put(URI.create("https://w3id.org/security#assertionMethod"),
                IdentifierDocument::assertion);
        RELS.put(URI.create("https://w3id.org/security#keyAgreementMethod"),
                IdentifierDocument::keyAgreement);
        RELS.put(URI.create("https://w3id.org/security#capabilityInvocationMethod"),
                IdentifierDocument::capabilityInvocation);
        RELS.put(URI.create("https://w3id.org/security#capabilityDelegationMethod"),
                IdentifierDocument::capabilityDelegation);
    }

    protected final Collection<IdentifierDocumentResolver> resolvers;

    /**
     * Creates a resolver that can delegate to the given
     * {@link IdentifierDocumentResolver}s when a controller document must be
     * fetched.
     *
     * @param resolvers a non-empty collection of document resolvers
     * @throws NullPointerException     if {@code resolvers} is {@code null}
     * @throws IllegalArgumentException if {@code resolvers} is empty
     */
    public VerificationMethodResolver(final Collection<IdentifierDocumentResolver> resolvers) {
        Objects.requireNonNull(resolvers, "resolvers must not be null");
        if (resolvers.isEmpty()) {
            throw new IllegalArgumentException("resolvers must not be empty");
        }
        this.resolvers = resolvers;
    }

    /**
     * Resolves a verification method with the given {@code methodId} from a set of
     * methods already retrieved from a document, ensuring the controller matches.
     *
     * @param methodId the identifier of the verification method
     * @param methods  the candidate verification methods
     * @param document the parent document that declares the methods
     * @return the matching {@link VerificationMethod}
     *
     * @throws VerificationMethodException with code
     *                                     {@link VerificationMethodException.Code#INVALID_VERIFICATION_METHOD}
     *                                     if no matching method is found or the
     *                                     controller mismatches
     * @throws NullPointerException        if any argument is {@code null}
     */
    public VerificationMethod resolve(final URI methodId,
            final Set<VerificationMethod> methods,
            final IdentifierDocument document) throws VerificationMethodException {
        final VerificationMethod method = methods.stream()
                .filter(m -> methodId.equals(m.id()))
                .findFirst()
                .orElseThrow(() -> new VerificationMethodException(
                        VerificationMethodException.Code.INVALID_VERIFICATION_METHOD,
                        "Verification method not found: " + methodId));

        if (!document.id().equals(method.controller())) {
            throw new VerificationMethodException(
                    VerificationMethodException.Code.INVALID_VERIFICATION_METHOD,
                    "Verification method controller mismatch, expected " + document.id()
                            + " but got " + method.controller());
        }

        return method;
    }

    /**
     * Resolves a verification method by {@code methodId} within the supplied
     * {@code document}, constrained by the verification {@code relation}.
     *
     * @param methodId the verification method identifier (must not be {@code null})
     * @param relation the verification relationship IRI (must not be {@code null})
     * @param document the controller document that lists the method (must not be
     *                 {@code null})
     * @return the matching {@link VerificationMethod}
     *
     * @throws VerificationMethodException with one of:
     *                                     <ul>
     *                                     <li>{@code INVALID_RELATIONSHIP_FOR_VERIFICATION_METHOD}</li>
     *                                     <li>{@code INVALID_VERIFICATION_METHOD}</li>
     *                                     </ul>
     * @throws NullPointerException        if any argument is {@code null}
     */
    public VerificationMethod resolve(final URI methodId,
            final URI relation,
            final IdentifierDocument document) throws VerificationMethodException {

        Objects.requireNonNull(methodId, "methodId must not be null");
        Objects.requireNonNull(relation, "relation must not be null");
        Objects.requireNonNull(document, "document must not be null");
        Objects.requireNonNull(document.id(), "document.id() must not be null");

        final Function<IdentifierDocument, Set<VerificationMethod>> methodProvider = RELS.get(relation);

        if (methodProvider == null) {
            throw new VerificationMethodException(
                    VerificationMethodException.Code.INVALID_RELATIONSHIP_FOR_VERIFICATION_METHOD,
                    "Unsupported verification relationship: " + relation);
        }

        final Set<VerificationMethod> methods = methodProvider.apply(document);
        if (methods == null || methods.isEmpty()) {
            throw new VerificationMethodException(
                    VerificationMethodException.Code.INVALID_VERIFICATION_METHOD,
                    "No verification methods for relation: " + relation);
        }

        return resolve(methodId, methods, document);
    }

    /**
     * Resolves a verification method by {@code methodId} and {@code relation},
     * fetching the controller document using the first
     * {@link IdentifierDocumentResolver} that
     * {@link IdentifierDocumentResolver#isAccepted(URI) accepts} the derived
     * document URI.
     *
     * <p>
     * The document URI is derived from {@code methodId} by stripping its fragment.
     * If no resolver accepts the URI, or the resolved document’s {@code id} does
     * not equal the derived URI, resolution fails.
     * </p>
     *
     * @param methodId the verification method identifier (must not be {@code null})
     * @param relation the verification relationship IRI (must not be {@code null})
     * @return the matching {@link VerificationMethod}
     *
     * @throws VerificationMethodException with one of:
     *                                     <ul>
     *                                     <li>{@code INVALID_METHOD_ID}</li>
     *                                     <li>{@code INVALID_CONTROLLER_DOCUMENT}</li>
     *                                     <li>{@code INVALID_CONTROLLER_DOCUMENT_ID}</li>
     *                                     <li>{@code INVALID_RELATIONSHIP_FOR_VERIFICATION_METHOD}</li>
     *                                     <li>{@code INVALID_VERIFICATION_METHOD}</li>
     *                                     </ul>
     * @throws NullPointerException        if any argument is {@code null}
     */
    public VerificationMethod resolve(final URI methodId,
            final URI relation) throws VerificationMethodException {

        Objects.requireNonNull(methodId, "methodId must not be null");
        Objects.requireNonNull(relation, "relation must not be null");

        final URI documentUri;
        try {
            // Strip fragment to obtain the controller document identifier
            documentUri = new URI(methodId.getScheme(), methodId.getSchemeSpecificPart(), null);

        } catch (URISyntaxException e) {
            throw new VerificationMethodException(
                    VerificationMethodException.Code.INVALID_METHOD_ID,
                    "Invalid methodId, failed to derive controller document URI",
                    e);
        }

        final IdentifierDocument document = resolvers.stream()
                .filter(r -> r.isAccepted(documentUri))
                .findFirst()
                .map(r -> r.resolve(documentUri))
                .orElseThrow(() -> new VerificationMethodException(
                        VerificationMethodException.Code.INVALID_CONTROLLER_DOCUMENT,
                        "No resolver accepted controller document: " + documentUri));

        if (!documentUri.equals(document.id())) {
            throw new VerificationMethodException(
                    VerificationMethodException.Code.INVALID_CONTROLLER_DOCUMENT_ID,
                    "Controller document id mismatch, expected " + documentUri
                            + " but got " + document.id());
        }

        return resolve(methodId, relation, document);
    }
}
