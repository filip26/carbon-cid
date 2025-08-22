package com.apicatalog.cid.jwk;

import java.net.URI;
import java.util.Map;

import com.apicatalog.cid.VerificationMethod;

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
    
    static ImmutableJsonWebKey immutable(URI id, URI controller, Map<String, Object> publicKey) {
        return immutable(id, controller, publicKey, null);
    }

    static ImmutableJsonWebKey immutable(URI id, URI controller, Map<String, Object> publicKey, Map<String, Object> secretKey) {
        return new ImmutableJsonWebKey(id, controller, publicKey, secretKey);
    }
}
