package com.apicatalog.cid;

/**
 * Thrown when resolution of a verification method fails.
 *
 * <p>
 * The {@link Code} enum indicates the reason for failure, allowing callers to
 * branch on error conditions.
 * </p>
 */
public class VerificationMethodException extends Exception {

    private static final long serialVersionUID = -9089776596803069731L;

    /** Categorical error code for resolution failures. */
    public enum Code {
        INVALID_RELATIONSHIP_FOR_VERIFICATION_METHOD,
        INVALID_VERIFICATION_METHOD,
        INVALID_CONTROLLER_DOCUMENT,
        INVALID_CONTROLLER_DOCUMENT_ID,
        INVALID_METHOD_ID
    }

    private final Code code;

    public VerificationMethodException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public VerificationMethodException(Code code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /** @return the error code indicating why resolution failed */
    public Code getCode() {
        return code;
    }
}
