package com.apicatalog.cid.multikey;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.cid.multicodec.MulticodecEncoded;

class MultikeyImpl implements Multikey {

    protected final URI id;
    protected final URI controller;

    protected final MulticodecEncoded publicKey;
    protected final MulticodecEncoded secretKey;

    protected Instant revoked;
    protected Instant expires;

    protected MultikeyImpl(
            URI id,
            URI controller,
            MulticodecEncoded publicKey,
            MulticodecEncoded privateKey) {
        this.id = id;
        this.controller = controller;
        this.publicKey = publicKey;
        this.secretKey = privateKey;
    }

    @Override
    public URI id() {
        return id;
    }

    @Override
    public String type() {
        return Multikey.TYPE;
    }

    @Override
    public URI controller() {
        return controller;
    }

    @Override
    public MulticodecEncoded publicKey() {
        return publicKey;
    }

    @Override
    public MulticodecEncoded secretKey() {
        return secretKey;
    }

    public MultikeyImpl revoked(Instant revoked) {
        this.revoked = revoked;
        return this;
    }

    @Override
    public Instant revoked() {
        return revoked;
    }

    @Override
    public Instant expires() {
        return expires;
    }

    public MultikeyImpl expires(Instant expires) {
        this.expires = expires;
        return this;
    }
}
