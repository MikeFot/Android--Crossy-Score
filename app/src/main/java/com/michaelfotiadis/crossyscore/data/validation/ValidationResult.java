package com.michaelfotiadis.crossyscore.data.validation;

/**
 *
 */
public interface ValidationResult {

    boolean isValid();

    ValidationCode getCode();

    String getMessage();
}
