package com.michaelfotiadis.crossyscore.common.responses;

/**
 * Custom Crossy Error object
 */
public final class CrossyError implements CrossyTimedElement {

    private final String mErrorMessage;
    private final CrossyErrorKind mKind;
    private final CrossyTimedElement mTimedElement;

    /**
     * Constructor for the CrossyError object
     *
     * @param errorMessage String message
     * @param kind         CrossyErrorKind enum
     */
    private CrossyError(final String errorMessage, final CrossyErrorKind kind) {
        this.mErrorMessage = errorMessage;
        this.mKind = kind;
        this.mTimedElement = new CrossyTimedElementImpl();
    }

    @Override
    public long getCreationTime() {
        return mTimedElement.getCreationTime();
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public CrossyErrorKind getKind() {
        return mKind;
    }

    @Override
    public String toString() {
        return "CrossyError{" +
                "ErrorMessage='" + mErrorMessage + '\'' +
                ", Kind=" + mKind +
                '}';
    }

    public static CrossyError from(final String message, final CrossyErrorKind kind) {
        return new CrossyError(message, kind);
    }

}
