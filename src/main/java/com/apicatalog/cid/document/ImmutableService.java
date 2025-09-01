package com.apicatalog.cid.document;

import java.net.URI;
import java.util.Collection;

final class ImmutableService implements Service {

    final URI id;

    final Collection<String> type;

    final Collection<ServiceEndpoint> endpoint;

    ImmutableService(
            final URI id,
            final Collection<String> type,
            final Collection<ServiceEndpoint> endpoint) {
        this.id = id;
        this.type = type;
        this.endpoint = endpoint;
    }

    @Override
    public URI id() {
        return id;
    }

    @Override
    public Collection<String> type() {
        return type;
    }

    @Override
    public Collection<ServiceEndpoint> endpoint() {
        return endpoint;
    }
}
