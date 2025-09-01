package com.apicatalog.cid;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

public interface Service {

    /**
     * The {@code id} of this service entry.
     *
     * @return the unique service identifier
     */
    URI id();

    /**
     * The {@code type} values of this service.
     *
     * @return one or more type strings
     */
    Collection<String> type();

    /**
     * The {@code serviceEndpoint} values of this service.
     *
     * @return one or more endpoints
     */
    Collection<ServiceEndpoint> endpoint();

    /**
     * Checks whether this service has the required properties: {@code id},
     * {@code type}, and at least one {@code serviceEndpoint}.
     *
     * @return {@code true} if valid
     */
    default boolean hasRequiredProperties() {
        return id() != null && type() != null && endpoint() != null && !endpoint().isEmpty();
    }

    /**
     * Creates a {@code Service} with a single type and a single endpoint.
     *
     * @param id       service id
     * @param type     service type
     * @param endpoint service endpoint
     * @return a new {@code Service}
     */
    static Service of(URI id, String type, ServiceEndpoint endpoint) {
        return new ImmutableService(id, Collections.singleton(type), Collections.singleton(endpoint));
    }

    /**
     * Creates a {@code Service} with a single type and multiple endpoints.
     *
     * @param id       service id
     * @param type     service type
     * @param endpoint service endpoints
     * @return a new {@code Service}
     */
    static Service of(URI id, String type, Collection<ServiceEndpoint> endpoint) {
        return new ImmutableService(id, Collections.singleton(type), endpoint);
    }

    /**
     * Creates a {@code Service} with multiple types and endpoints.
     *
     * @param id       service id
     * @param type     service types
     * @param endpoint service endpoints
     * @return a new {@code Service}
     */
    static Service of(URI id, Collection<String> type, Collection<ServiceEndpoint> endpoint) {
        return new ImmutableService(id, type, endpoint);
    }
}
