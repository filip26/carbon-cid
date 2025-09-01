package com.apicatalog.cid.document;

import java.net.URI;
import java.time.Instant;

/**
 * Represents a <a href="https://www.w3.org/TR/cid-1.0/#verification-methods">
 * verification method</a> declaration in a Controlled Identifier Document.
 *
 * <p>
 * A verification method defines cryptographic material and its controller. It
 * is referenced by verification relationships such as authentication,
 * assertion, key agreement, capability invocation, or capability delegation.
 * Each method is uniquely identified by a URI within the document.
 * </p>
 */
public interface VerificationMethod {

    /**
     * Returns the unique identifier of this verification method.
     *
     * @return the verification method identifier as a {@link URI}
     */
    URI id();

    /**
     * Returns the type of this verification method.
     *
     * <p>
     * The value typically corresponds to a verification method type defined by the
     * W3C or related specifications.
     * </p>
     *
     * @return the verification method type
     */
    String type();

    /**
     * Returns the controller of this verification method.
     *
     * @return the controller as a {@link URI}
     */
    URI controller();

    /**
     * Returns the timestamp when this verification method was revoked, or
     * {@code null} if it has not been revoked.
     *
     * @return the revocation time, or {@code null} if not revoked
     */
    default Instant revoked() {
        return null;
    }

    /**
     * Returns the expiration time of this verification method, or {@code null} if
     * it does not expire.
     *
     * @return the expiration time, or {@code null} if none is set
     */
    default Instant expires() {
        return null;
    }

    /**
     * Checks whether this verification method has the required properties:
     * {@link #id()}, {@link #type()}, and {@link #controller()}.
     *
     * @return {@code true} if all required properties are present, {@code false}
     *         otherwise
     */
    default boolean hasRequiredProperties() {
        return id() != null && type() != null && controller() != null;
    }
}
