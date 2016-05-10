package com.michaelfotiadis.crossyscore.data.validation.validators;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;

/**
 *
 */
public interface Validator<T extends AppModel> {

    ValidationResult validate(T item);
}
