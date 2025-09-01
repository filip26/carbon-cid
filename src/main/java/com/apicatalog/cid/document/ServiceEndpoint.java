package com.apicatalog.cid.document;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Represents a <a href="https://www.w3.org/TR/cid-1.0/#services"> service
 * endpoint</a> entry in a Controlled Identifier Document.
 *
 * <p>
 * A service endpoint describes how to interact with a service associated with a
 * Controlled Identifier. It is referenced from a {@link Service} and may
 * include an {@code id}, optional {@code type} values, and other
 * endpoint-specific properties.
 * </p>
 *
 * <p>
 * The only required property is {@link #id()}, which uniquely identifies the
 * endpoint.
 * </p>
 */
public interface ServiceEndpoint {

    /**
     * Returns the identifier of this service endpoint.
     *
     * @return the endpoint identifier as a {@link URI}, never {@code null} if valid
     */
    URI id();

    /**
     * Returns the type values associated with this endpoint.
     *
     * @return a collection of type strings, or an empty collection if none are
     *         defined
     */
    default Collection<String> type() {
        return Collections.emptySet();
    }

    /**
     * Checks whether this endpoint has the required {@code id} property.
     *
     * @return {@code true} if {@link #id()} is not {@code null}
     */
    default boolean hasRequiredProperties() {
        return id() != null;
    }

    /**
     * Creates a {@code ServiceEndpoint} with the given {@code id}.
     *
     * @param id the endpoint identifier (must not be {@code null})
     * @return a new immutable {@code ServiceEndpoint}
     * @throws NullPointerException if {@code id} is {@code null}
     */
    static ServiceEndpoint of(URI id) {
        Objects.requireNonNull(id, "ServiceEndpoint id must not be null.");
        return new ImmutableServiceEndpoint(id);
    }
}
