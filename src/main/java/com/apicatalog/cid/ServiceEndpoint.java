package com.apicatalog.cid;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public interface ServiceEndpoint {

    URI id();

    default Collection<String> type() {
        return Collections.emptySet();
    }

    /**
     * Checks whether this endpoint has the required {@code id} property.
     *
     * @return {@code true} if valid
     */
    default boolean hasRequiredProperties() {
        return id() != null;
    }

    /**
     * Creates a {@code ServiceEndpoint} with the given {@code id}.
     *
     * @param id endpoint identifier (must not be {@code null})
     * @return a new {@code ServiceEndpoint}
     * @throws NullPointerException if {@code id} is {@code null}
     */
    static ServiceEndpoint of(URI id) {
        Objects.requireNonNull(id);
        return new ImmutableServiceEndpoint(id);
    }
}
