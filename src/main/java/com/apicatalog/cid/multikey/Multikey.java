package com.apicatalog.cid.multikey;

import java.net.URI;

import com.apicatalog.cid.VerificationMethod;
import com.apicatalog.cid.multicodec.MulticodecEncoded;

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
    
    static MultikeyImpl of(URI id, URI controller, MulticodecEncoded publicKey) {
        return of(id, controller, publicKey, null);
    }

    static MultikeyImpl of(URI id, URI controller, MulticodecEncoded publicKey, MulticodecEncoded secretKey) {
        return new MultikeyImpl(id, controller, publicKey, secretKey);
    }

}
