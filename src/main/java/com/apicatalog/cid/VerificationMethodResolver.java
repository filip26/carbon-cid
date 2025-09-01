package com.apicatalog.cid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.apicatalog.cid.document.IdentifierDocument;
import com.apicatalog.cid.document.VerificationMethod;

/**
 * Retrieves a verification method from {@link IdentifierDocument}.
 */
public class VerificationMethodResolver {

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

    public VerificationMethodResolver(Collection<IdentifierDocumentResolver> resolvers) {
        this.resolvers = resolvers;
    }

    public VerificationMethod retrieve(final URI methodId, final URI relation) {

        try {
            // remove fragment
            final URI documentUri = new URI(methodId.getScheme(), methodId.getSchemeSpecificPart(), null);

            // resolve controller document
            final IdentifierDocument document = resolvers.stream()
                    .filter(r -> r.isAccepted(documentUri))
                    .findFirst()
                    .map(r -> r.resolve(documentUri))
                    .orElseThrow(() -> new IllegalArgumentException("INVALID_CONTROLLER_DOCUMENT"));

            if (!document.id().equals(documentUri)) {
                throw new IllegalArgumentException("INVALID_CONTROLLER_DOCUMENT_ID");
            }

            final Function<IdentifierDocument, Set<VerificationMethod>> methodProvider = RELS.get(relation);

            if (methodProvider == null) {
                throw new IllegalArgumentException("INVALID_RELATIONSHIP_FOR_VERIFICATION_METHOD");
            }

            Set<VerificationMethod> methods = methodProvider.apply(document);

            if (methods == null || methods.isEmpty()) {
                throw new IllegalArgumentException("INVALID_VERIFICATION_METHOD");
            }

            final VerificationMethod method = methods.stream()
                    .filter(m -> methodId.equals(m.id()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("INVALID_VERIFICATION_METHOD"));

            if (!documentUri.equals(method.controller())) {
                throw new IllegalArgumentException("INVALID_VERIFICATION_METHOD");
            }

            return method;

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
