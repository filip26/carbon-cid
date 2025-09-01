package com.apicatalog.cid.document;

import java.net.URI;
import java.time.Instant;

final class ImmutableVerificationMethod implements VerificationMethod {

    final URI id;
    
    ImmutableVerificationMethod(URI id) {
        this.id = id;
    }
    
    @Override
    public URI id() {
        return id;
    }
    
    @Override
    public String type() {
        return null;
    }

    @Override
    public URI controller() {
        return null;
    }

    @Override
    public Instant revoked() {
        return null;
    }

    @Override
    public Instant expires() {
        return null;
    }
}
