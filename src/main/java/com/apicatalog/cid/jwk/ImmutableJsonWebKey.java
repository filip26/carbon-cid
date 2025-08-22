package com.apicatalog.cid.jwk;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

class ImmutableJsonWebKey implements JsonWebKey {

    protected final URI id;
    protected final URI controller;

    protected final Map<String, Object> publicKey;
    protected final Map<String, Object> secretKey;

    protected Instant revoked;
    protected Instant expires;

    protected ImmutableJsonWebKey(
            URI id,
            URI controller,
            Map<String, Object> publicKey,
            Map<String, Object> secretKey) {
        this.id = id;
        this.controller = controller;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
    }

    @Override
    public URI id() {
        return id;
    }

    @Override
    public URI controller() {
        return controller;
    }

    @Override
    public Instant revoked() {
        return revoked;
    }

    @Override
    public Instant expires() {
        return expires;
    }

    @Override
    public Map<String, Object> publicKey() {
        return publicKey;
    }

    @Override
    public Map<String, Object> secretKey() {
        return secretKey;
    }

    public ImmutableJsonWebKey expires(Instant expires) {
        this.expires = expires;
        return this;
    }

    public ImmutableJsonWebKey revoked(Instant revoked) {
        this.revoked = revoked;
        return this;
    }
}
