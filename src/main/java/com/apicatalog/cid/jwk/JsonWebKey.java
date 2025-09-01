package com.apicatalog.cid.jwk;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

import com.apicatalog.cid.document.VerificationMethod;

public interface JsonWebKey extends VerificationMethod {

    static final String TYPE = "https://w3id.org/security#JsonWebKey";

    @Override
    default String type() {
        return TYPE;
    }

    Map<String, Object> publicKey();

    default Map<String, Object> secretKey() {
        return null;
    }
    
    static ImmutableJsonWebKey of(URI id, URI controller, Map<String, Object> publicKey) {
        return new ImmutableJsonWebKey(id, controller, publicKey, null, null, null);
    }

    static ImmutableJsonWebKey of(URI id, URI controller, Map<String, Object> publicKey, Instant revoked, Instant expires) {
        return new ImmutableJsonWebKey(id, controller, publicKey, null, revoked, expires);
    }

    static ImmutableJsonWebKey of(URI id, URI controller, Map<String, Object> publicKey, Map<String, Object> secretKey) {
        return new ImmutableJsonWebKey(id, controller, publicKey, secretKey, null, null);
    }
    
    static ImmutableJsonWebKey of(URI id, URI controller, Map<String, Object> publicKey, Map<String, Object> secretKey, Instant revoked, Instant expires) {
        return new ImmutableJsonWebKey(id, controller, publicKey, secretKey, revoked, expires);
    }
}
