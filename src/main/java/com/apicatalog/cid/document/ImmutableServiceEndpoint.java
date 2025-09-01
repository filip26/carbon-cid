package com.apicatalog.cid.document;

import java.net.URI;

final class ImmutableServiceEndpoint implements ServiceEndpoint {

    final URI id;

    ImmutableServiceEndpoint(URI id) {
        this.id = id;
    }

    @Override
    public URI id() {
        return id;
    }
}
