package com.michaelfotiadis.crossyscore.data.validation;

/**
 *
 */
public final class ValidationResultImpl implements ValidationResult {

    final ValidationCode code;
    final String message;

    private ValidationResultImpl(final Builder builder) {
        code = builder.code;
        message = builder.message;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final ValidationResultImpl copy) {
        final Builder builder = new Builder();
        builder.code = copy.code;
        builder.message = copy.message;
        return builder;
    }

    public static ValidationResult makeValid() {
        return newBuilder().withCode(ValidationCode.VALID).withMessage("").build();
    }

    public static ValidationResult makeInvalidContent(final String message) {
        return newBuilder().withCode(ValidationCode.INVALID_CONTENT).withMessage(message).build();
    }

    public static ValidationResult makeNullContent(final String message) {
        return newBuilder().withCode(ValidationCode.NULL_CONTENT).withMessage(message).build();
    }

    public static ValidationResult make(final ValidationCode code, final String message) {
        return newBuilder().withCode(code).withMessage(message).build();
    }

    @Override
    public ValidationCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isValid() {
        return code.equals(ValidationCode.VALID);
    }

    public static final class Builder {

        private ValidationCode code;
        private String message;

        private Builder() {
        }

        public Builder withCode(final ValidationCode val) {
            code = val;
            return this;
        }

        public Builder withMessage(final String val) {
            message = val;
            return this;
        }

        public ValidationResultImpl build() {
            return new ValidationResultImpl(this);
        }
    }

    @Override
    public String toString() {
        return "ValidationResultImpl{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
