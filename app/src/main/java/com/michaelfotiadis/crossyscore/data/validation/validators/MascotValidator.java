package com.michaelfotiadis.crossyscore.data.validation.validators;

import android.text.TextUtils;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResultImpl;

/**
 *
 */
public class MascotValidator implements Validator<Mascot> {

    @Override
    public ValidationResult validate(final Mascot mascot) {
        if (mascot == null) {
            return ValidationResultImpl.makeNullContent("Mascot cannot be null");
        } else if (TextUtils.isEmpty(mascot.getName())) {
            return ValidationResultImpl.makeNullContent("Name of the mascot cannot be empty");
        } else {
            return ValidationResultImpl.makeValid();
        }
    }
}
