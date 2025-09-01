package com.apicatalog.cid.multikey;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.cid.datatype.MulticodecEncoded;

final class ImmutableMultikey implements Multikey {

    final URI id;
    final URI controller;

    final MulticodecEncoded publicKey;
    final MulticodecEncoded secretKey;

    final Instant revoked;
    final Instant expires;

    ImmutableMultikey(
            URI id,
            URI controller,
            MulticodecEncoded publicKey,
            MulticodecEncoded privateKey,
            Instant revoked,
            Instant expires) {
        this.id = id;
        this.controller = controller;
        this.publicKey = publicKey;
        this.secretKey = privateKey;
        this.revoked = revoked;
        this.expires = expires;
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

    @Override
    public Instant revoked() {
        return revoked;
    }

    @Override
    public Instant expires() {
        return expires;
    }
}
