package com.apicatalog.cid;

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
