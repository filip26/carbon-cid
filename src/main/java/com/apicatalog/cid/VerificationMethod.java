package com.apicatalog.cid;

import java.net.URI;
import java.time.Instant;

/**
 * Represents a verification method declaration.
 * 
 * https://www.w3.org/TR/cid-1.0/#verification-methods
 */
public interface VerificationMethod {

    URI id();

    String type();

    URI controller();

    Instant revoked();

    Instant expires();
}