package com.apicatalog.cid.document;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;

/**
 * Represents a <a href="https://www.w3.org/TR/cid-1.0/#services"> service
 * declaration</a> in a Controlled Identifier Document.
 *
 * <p>
 * A service entry associates an identifier with one or more types and service
 * endpoints. Services describe how interactions with the subject of a
 * Controlled Identifier can be performed, for example by exposing APIs or other
 * network-accessible resources.
 * </p>
 *
 * <p>
 * Each service has three required properties:
 * </p>
 * <ul>
 * <li>{@code id} – a unique identifier for the service entry,</li>
 * <li>{@code type} – one or more service type values,</li>
 * <li>{@code serviceEndpoint} – one or more endpoints describing how to
 * interact with the service.</li>
 * </ul>
 */
public interface Service {

    /**
     * Returns the {@code id} of this service entry.
     *
     * @return the unique service identifier as a {@link URI}
     */
    URI id();

    /**
     * Returns the {@code type} values of this service.
     *
     * @return a collection of one or more type strings
     */
    Collection<String> type();

    /**
     * Returns the {@code serviceEndpoint} values of this service.
     *
     * @return a collection of one or more {@link ServiceEndpoint} values
     */
    Collection<ServiceEndpoint> endpoint();

    /**
     * Checks whether this service entry contains the required properties:
     * {@link #id()}, {@link #type()}, and at least one {@link #endpoint()}.
     *
     * @return {@code true} if all required properties are present
     */
    default boolean hasRequiredProperties() {
        return id() != null && type() != null && endpoint() != null && !endpoint().isEmpty();
    }

    /**
     * Creates a {@code Service} with a single type and a single endpoint.
     *
     * @param id       the service identifier
     * @param type     the service type
     * @param endpoint the service endpoint
     * @return a new immutable {@code Service} instance
     */
    static Service of(URI id, String type, ServiceEndpoint endpoint) {
        return new ImmutableService(id, Collections.singleton(type), Collections.singleton(endpoint));
    }

    /**
     * Creates a {@code Service} with a single type and multiple endpoints.
     *
     * @param id       the service identifier
     * @param type     the service type
     * @param endpoint the service endpoints
     * @return a new immutable {@code Service} instance
     */
    static Service of(URI id, String type, Collection<ServiceEndpoint> endpoint) {
        return new ImmutableService(id, Collections.singleton(type), endpoint);
    }

    /**
     * Creates a {@code Service} with multiple types and endpoints.
     *
     * @param id       the service identifier
     * @param type     the service types
     * @param endpoint the service endpoints
     * @return a new immutable {@code Service} instance
     */
    static Service of(URI id, Collection<String> type, Collection<ServiceEndpoint> endpoint) {
        return new ImmutableService(id, type, endpoint);
    }
}
