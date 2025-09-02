/**
 * Provides core APIs for working with
 * <a href="https://www.w3.org/TR/cid-1.0/">Controlled Identifiers (CID)</a> as
 * defined by the W3C.
 *
 * <p>
 * This package includes:
 * </p>
 * <ul>
 * <li>{@link com.apicatalog.cid.IdentifierDocumentResolver} – abstraction for
 * resolving {@code IdentifierDocument} instances from a
 * {@link java.net.URI}.</li>
 * <li>{@link com.apicatalog.cid.VerificationMethodResolver} – utilities for
 * resolving verification methods declared within an identifier document,
 * including checks for supported verification relationships and controller
 * consistency.</li>
 * <li>Exception types such as
 * {@link com.apicatalog.cid.VerificationMethodException} with error codes that
 * classify resolution failures.</li>
 * </ul>
 *
 * <p>
 * Together these classes allow applications to fetch and process Controlled
 * Identifier Documents, and to extract the cryptographic material (verification
 * methods) necessary for authentication, assertion, key agreement, capability
 * invocation, and capability delegation.
 * </p>
 */
package com.apicatalog.cid;
