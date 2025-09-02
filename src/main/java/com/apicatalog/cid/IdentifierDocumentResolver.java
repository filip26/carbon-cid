package com.apicatalog.cid;

import java.net.URI;

import com.apicatalog.cid.document.IdentifierDocument;

/**
 * Resolves {@link IdentifierDocument} instances for given identifiers.
 *
 * <p>
 * An {@code IdentifierDocumentResolver} provides the mechanism to determine
 * whether it can handle a specific identifier and, if so, resolve that
 * identifier to its corresponding {@link IdentifierDocument}.
 * </p>
 *
 * <p>
 * This abstraction allows multiple resolver implementations to coexist, each
 * supporting different identifier schemes, persistence layers, or resolution
 * protocols.
 * </p>
 *
 * @see <a href="https://www.w3.org/TR/cid-1.0/">W3C Controlled Identifiers
 *      1.0</a>
 */
public interface IdentifierDocumentResolver {

    /**
     * Checks whether this resolver accepts the given identifier.
     *
     * @param id the identifier to test (must not be {@code null})
     * @return {@code true} if this resolver can resolve the identifier,
     *         {@code false} otherwise
     */
    boolean isAccepted(URI id);

    /**
     * Resolves the given identifier into a {@link IdentifierDocument}.
     *
     * @param id the identifier to resolve (must not be {@code null})
     * @return the resolved {@link IdentifierDocument}
     * @throws IllegalArgumentException if the identifier cannot be resolved
     */
    IdentifierDocument resolve(URI id);
}
