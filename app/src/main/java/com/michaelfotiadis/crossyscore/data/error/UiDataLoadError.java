package com.michaelfotiadis.crossyscore.data.error;

/**
 *
 */
public final class UiDataLoadError {

    private final ErrorKind kind;
    private final CharSequence message;
    private final boolean recoverable;

    public UiDataLoadError(final CharSequence message, final ErrorKind kind, final boolean recoverable) {
        this.message = message;
        this.kind = kind;
        this.recoverable = recoverable;
    }

    public ErrorKind getKind() {
        return kind;
    }

    public CharSequence getMessage() {
        return message;
    }

    public boolean isRecoverable() {
        return recoverable;
    }

    @Override
    public String toString() {
        return "UiDataLoadError{" +
                "kind=" + kind +
                ", message=" + message +
                ", recoverable=" + recoverable +
                '}';
    }

    public enum ErrorKind {
        UNKNOWN,
        NO_NETWORK,
        NO_DATA,
        NO_LOCATION
    }
}
