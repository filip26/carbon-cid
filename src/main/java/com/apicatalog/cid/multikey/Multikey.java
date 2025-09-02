package com.apicatalog.cid.multikey;

import java.net.URI;
import java.time.Instant;

import com.apicatalog.cid.datatype.MulticodecEncoded;
import com.apicatalog.cid.document.VerificationMethod;

/**
 * Verification method of type
 * <a href="https://www.w3.org/TR/cid-1.0/#Multikey">Multikey</a> as defined by
 * the W3C Controlled Identifiers 1.0 specification.
 *
 * <p>
 * The verification method type identifier is
 * {@code https://w3id.org/security#Multikey}.
 * </p>
 */
public interface Multikey extends VerificationMethod {

    /** Verification method type identifier for Multikey. */
    String TYPE = "https://w3id.org/security#Multikey";

    /** {@inheritDoc} */
    @Override
    default String type() {
        return TYPE;
    }

    /**
     * Returns the public key encoded using the multicodec format.
     *
     * @return multicodec-encoded public key (never {@code null})
     */
    MulticodecEncoded publicKey();

    /**
     * Returns the secret/private key encoded using the multicodec format, if
     * present.
     *
     * @return multicodec-encoded secret key, or {@code null} if not provided
     */
    default MulticodecEncoded secretKey() {
        return null;
    }

    /**
     * Creates a {@code Multikey} with identifier, controller, and public key.
     *
     * @param id         unique identifier of this verification method
     * @param controller controlling identifier
     * @param publicKey  multicodec-encoded public key (must not be {@code null})
     * @return immutable {@code Multikey}
     */
    static Multikey of(final URI id,
            final URI controller,
            final MulticodecEncoded publicKey) {
        return new ImmutableMultikey(id, controller, publicKey, null, null, null);
    }

    /**
     * Creates a {@code Multikey} with identifier, controller, public key, and
     * lifecycle metadata.
     *
     * @param id         unique identifier of this verification method
     * @param controller controlling identifier
     * @param publicKey  multicodec-encoded public key (must not be {@code null})
     * @param revoked    revocation timestamp, or {@code null} if not revoked
     * @param expires    expiration timestamp, or {@code null} if it does not expire
     * @return immutable {@code Multikey}
     */
    static Multikey of(final URI id,
            final URI controller,
            final MulticodecEncoded publicKey,
            final Instant revoked,
            final Instant expires) {
        return new ImmutableMultikey(id, controller, publicKey, null, revoked, expires);
    }

    /**
     * Creates a {@code Multikey} with identifier, controller, public and secret
     * keys.
     *
     * @param id         unique identifier of this verification method
     * @param controller controlling identifier
     * @param publicKey  multicodec-encoded public key (must not be {@code null})
     * @param secretKey  multicodec-encoded secret key (may be {@code null})
     * @return immutable {@code Multikey}
     */
    static Multikey of(final URI id,
            final URI controller,
            final MulticodecEncoded publicKey,
            final MulticodecEncoded secretKey) {
        return new ImmutableMultikey(id, controller, publicKey, secretKey, null, null);
    }

    /**
     * Creates a {@code Multikey} with identifier, controller, public and secret
     * keys, and lifecycle metadata.
     *
     * @param id         unique identifier of this verification method
     * @param controller controlling identifier
     * @param publicKey  multicodec-encoded public key (must not be {@code null})
     * @param secretKey  multicodec-encoded secret key (may be {@code null})
     * @param revoked    revocation timestamp, or {@code null} if not revoked
     * @param expires    expiration timestamp, or {@code null} if it does not expire
     * @return immutable {@code Multikey}
     */
    static Multikey of(final URI id,
            final URI controller,
            final MulticodecEncoded publicKey,
            final MulticodecEncoded secretKey,
            final Instant revoked,
            final Instant expires) {
        return new ImmutableMultikey(id, controller, publicKey, secretKey, revoked, expires);
    }
}
