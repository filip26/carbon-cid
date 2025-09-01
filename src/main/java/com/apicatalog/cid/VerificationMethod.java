package com.apicatalog.cid;

import java.net.URI;
import java.time.Instant;

/**
 * Represents a verification method declaration.
 * 
 * https://www.w3.org/TR/cid-1.0/#verification-methods
 */
public interface VerificationMethod {

    /**
     * The unique identifier of this verification method.
     *
     * @return URI identifier
     */
    URI id();

    /**
     * The type of this verification method.
     *
     * @return verification method type
     */
    String type();

    /**
     * The controlling URI of this verification method.
     *
     * @return controller URI
     */
    URI controller();

    default Instant revoked() {
        return null;
    }

    default Instant expires() {
        return null;
    }
    
    /**
     * Checks whether this verification method has the required properties:
     * {@code id}, {@code type}, and {@code controller}.
     *
     * @return {@code true} if valid
     */
    default boolean hasRequiredProperties() {
        return id() != null && type() != null && controller() != null;
    }

    
}