package com.apicatalog.cid.jwk;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

import com.apicatalog.cid.document.VerificationMethod;

/**
 * Verification method of type
 * <a href="https://www.w3.org/TR/cid-1.0/#JsonWebKey">JsonWebKey</a> as defined
 * by the W3C Controlled Identifiers 1.0 specification.
 *
 * <p>
 * The verification method type identifier is
 * {@code https://w3id.org/security#JsonWebKey}.
 * </p>
 */
public interface JsonWebKey extends VerificationMethod {

    /** Verification method type identifier for JsonWebKey. */
    String TYPE = "https://w3id.org/security#JsonWebKey";

    /** {@inheritDoc} */
    @Override
    default String type() {
        return TYPE;
    }

    /**
     * Returns the public JWK members for this verification method. Typical fields
     * include {@code kty}, {@code crv}, {@code x}, {@code y}, etc.
     *
     * @return map of public JWK members (never {@code null})
     */
    Map<String, Object> publicKey();

    /**
     * Returns the private/secret JWK members, if present. Typical fields include
     * {@code d} (and others depending on key type).
     *
     * @return map of secret JWK members, or {@code null} if not provided
     */
    default Map<String, Object> secretKey() {
        return null;
    }

    /**
     * Creates a {@code JsonWebKey} with identifier, controller, and public key
     * members.
     *
     * @param id         the verification method identifier
     * @param controller the controlling identifier
     * @param publicKey  public JWK members (must not be {@code null})
     * @return immutable {@code JsonWebKey}
     */
    static ImmutableJsonWebKey of(final URI id,
            final URI controller,
            final Map<String, Object> publicKey) {
        return new ImmutableJsonWebKey(id, controller, publicKey, null, null, null);
    }

    /**
     * Creates a {@code JsonWebKey} with identifier, controller, public key members,
     * and lifecycle metadata.
     *
     * @param id         the verification method identifier
     * @param controller the controlling identifier
     * @param publicKey  public JWK members (must not be {@code null})
     * @param revoked    revocation timestamp, or {@code null} if not revoked
     * @param expires    expiration timestamp, or {@code null} if it does not expire
     * @return immutable {@code JsonWebKey}
     */
    static ImmutableJsonWebKey of(final URI id,
            final URI controller,
            final Map<String, Object> publicKey,
            final Instant revoked,
            final Instant expires) {
        return new ImmutableJsonWebKey(id, controller, publicKey, null, revoked, expires);
    }

    /**
     * Creates a {@code JsonWebKey} with identifier, controller, public and secret
     * key members.
     *
     * @param id         the verification method identifier
     * @param controller the controlling identifier
     * @param publicKey  public JWK members (must not be {@code null})
     * @param secretKey  secret JWK members (may be {@code null})
     * @return immutable {@code JsonWebKey}
     */
    static ImmutableJsonWebKey of(final URI id,
            final URI controller,
            final Map<String, Object> publicKey,
            final Map<String, Object> secretKey) {
        return new ImmutableJsonWebKey(id, controller, publicKey, secretKey, null, null);
    }

    /**
     * Creates a {@code JsonWebKey} with identifier, controller, public and secret
     * key members, and lifecycle metadata.
     *
     * @param id         the verification method identifier
     * @param controller the controlling identifier
     * @param publicKey  public JWK members (must not be {@code null})
     * @param secretKey  secret JWK members (may be {@code null})
     * @param revoked    revocation timestamp, or {@code null} if not revoked
     * @param expires    expiration timestamp, or {@code null} if it does not expire
     * @return immutable {@code JsonWebKey}
     */
    static ImmutableJsonWebKey of(final URI id,
            final URI controller,
            final Map<String, Object> publicKey,
            final Map<String, Object> secretKey,
            final Instant revoked,
            final Instant expires) {
        return new ImmutableJsonWebKey(id, controller, publicKey, secretKey, revoked, expires);
    }
}
