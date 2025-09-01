/**
 * Provides interfaces for representing
 * <a href="https://www.w3.org/TR/cid-1.0/">Controlled Identifier Documents</a>.
 *
 * <p>
 * A Controlled Identifier Document expresses cryptographic material and
 * metadata associated with a Controlled Identifier. It specifies controller(s),
 * verification methods, verification relationships, and service endpoints
 * needed to use the identifier in secure interactions.
 * </p>
 *
 * <p>
 * This package defines the following core abstractions:
 * </p>
 * <ul>
 * <li>{@link com.apicatalog.cid.document.IdentifierDocument} – the root
 * representation of a Controlled Identifier Document,</li>
 * <li>{@link com.apicatalog.cid.document.VerificationMethod} – a declaration of
 * cryptographic material that can be bound into verification
 * relationships,</li>
 * <li>{@link com.apicatalog.cid.document.Service} – a service declaration
 * describing how to interact with the subject,</li>
 * <li>{@link com.apicatalog.cid.document.ServiceEndpoint} – an endpoint entry
 * referenced from a {@code Service}.</li>
 * </ul>
 *
 * <p>
 * All interfaces provide default methods to check for required properties as
 * defined by the specification.
 * </p>
 *
 * @see <a href="https://www.w3.org/TR/cid-1.0/">W3C Controlled Identifiers
 *      1.0</a>
 */
package com.apicatalog.cid.document;
