package com.apicatalog.cid.multikey;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.cid.datatype.MulticodecEncoded;
import com.apicatalog.cid.document.VerificationMethod;

public interface Multikey extends VerificationMethod {

    static final String TYPE = "https://w3id.org/security#Multikey";

    @Override
    default String type() {
        return TYPE;
    }

    MulticodecEncoded publicKey();

    default MulticodecEncoded secretKey() {
        return null;
    }
    
    static Multikey of(URI id, URI controller, MulticodecEncoded publicKey) {
        return new ImmutableMultikey(id, controller, publicKey, null, null, null);
    }

    static Multikey of(URI id, URI controller, MulticodecEncoded publicKey, Instant revoked, Instant expires) {
        return new ImmutableMultikey(id, controller, publicKey, null, revoked, expires);
    }

    static Multikey of(URI id, URI controller, MulticodecEncoded publicKey, MulticodecEncoded secretKey) {
        return new ImmutableMultikey(id, controller, publicKey, secretKey, null, null);
    }

    static Multikey of(URI id, URI controller, MulticodecEncoded publicKey, MulticodecEncoded secretKey, Instant revoked, Instant expires) {
        return new ImmutableMultikey(id, controller, publicKey, secretKey, revoked, expires);
    }

}
