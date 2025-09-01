package com.apicatalog.cid.jwk;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

final class ImmutableJsonWebKey implements JsonWebKey {

    final URI id;
    final URI controller;

    final Map<String, Object> publicKey;
    final Map<String, Object> secretKey;

    final Instant revoked;
    final Instant expires;

    ImmutableJsonWebKey(
            URI id,
            URI controller,
            Map<String, Object> publicKey,
            Map<String, Object> secretKey,
            Instant revoked,
            Instant expires) {
        this.id = id;
        this.controller = controller;
        this.publicKey = publicKey;
        this.secretKey = secretKey;
        this.revoked = revoked;
        this.expires = expires;
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
}
