package com.apicatalog.cid.document;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a Controlled Identifier Document as defined by the W3C Controlled
 * Identifiers 1.0 specification.
 *
 * <p>
 * A Controlled Identifier Document describes the relationships between
 * cryptographic material associated with a Controlled Identifier and its
 * purpose. It specifies controller(s), verification methods, verification
 * relationships (authentication, assertion, key agreement, capability
 * invocation, and capability delegation), as well as service endpoints.
 * </p>
 *
 * <p>
 * <strong>Note:</strong> This API does not assume or require a dedicated URI
 * scheme for Controlled Identifiers. The identifier is exposed as a generic
 * {@link java.net.URI} via {@link #id()}.
 * </p>
 *
 * @see <a href="https://www.w3.org/TR/cid-1.0/">W3C Controlled Identifiers
 *      1.0</a>
 */
public interface IdentifierDocument {

    /**
     * Returns the identifier that this document describes.
     *
     * <p>
     * No specific URI scheme is assumed.
     * </p>
     *
     * @return the identifier as a {@link URI}
     */
    URI id();

    /**
     * Returns the entities that are controllers of this identifier.
     *
     * @return a collection of controller URIs, or an empty collection if none are
     *         specified
     */
    default Collection<URI> controller() {
        return Collections.emptySet();
    }

    /**
     * Returns all verification methods defined in this document.
     *
     * @return a collection of verification methods, or an empty collection if none
     *         are defined
     */
    default Collection<VerificationMethod> verification() {
        return Collections.emptySet();
    }

    /**
     * Returns alternative identifiers for the same subject.
     *
     * @return a collection of URIs, or an empty collection if none are provided
     */
    default Collection<URI> alsoKnownAs() {
        return Collections.emptySet();
    }

    /**
     * Returns verification methods that can be used for authentication.
     *
     * @return a set of verification methods, or an empty set if not specified
     */
    default Set<VerificationMethod> authentication() {
        return Collections.emptySet();
    }

    /**
     * Returns verification methods that can be used for assertions.
     *
     * @return a set of verification methods, or an empty set if not specified
     */
    default Set<VerificationMethod> assertion() {
        return Collections.emptySet();
    }

    /**
     * Returns verification methods that can be used for key agreement.
     *
     * @return a set of verification methods, or an empty set if not specified
     */
    default Set<VerificationMethod> keyAgreement() {
        return Collections.emptySet();
    }

    /**
     * Returns verification methods that can be used for capability invocation.
     *
     * @return a set of verification methods, or an empty set if not specified
     */
    default Set<VerificationMethod> capabilityInvocation() {
        return Collections.emptySet();
    }

    /**
     * Returns verification methods that can be used for capability delegation.
     *
     * @return a set of verification methods, or an empty set if not specified
     */
    default Set<VerificationMethod> capabilityDelegation() {
        return Collections.emptySet();
    }

    /**
     * Returns service endpoints associated with this identifier.
     *
     * @return a set of services, or an empty set if none are defined
     */
    default Set<Service> service() {
        return Collections.emptySet();
    }

    /**
     * Checks whether this document has the required {@code id} property.
     *
     * @return {@code true} if {@link #id()} is not {@code null}
     */
    default boolean hasRequiredProperties() {
        return id() != null;
    }
}
