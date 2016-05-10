package com.michaelfotiadis.crossyscore.data.validation.validators;

import android.text.TextUtils;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResultImpl;

/**
 *
 */
public class ScoreValidator implements Validator<Score> {

    private static ValidationResult validateValue(final Integer value) {
        if (value == null) {
            return ValidationResultImpl.makeNullContent("Score cannot be empty");
        } else if (value < 0) {
            return ValidationResultImpl.makeInvalidContent("Score cannot be negative");
        } else {
            return ValidationResultImpl.makeValid();
        }
    }

    private static ValidationResult validateTimeStamp(final Long timeStamp) {
        if (timeStamp == null) {
            return ValidationResultImpl.makeNullContent("Time stamp cannot be empty");
        } else if (timeStamp < 0) {
            return ValidationResultImpl.makeInvalidContent("Invalid time stamp");
        } else {
            return ValidationResultImpl.makeValid();
        }
    }

    private static ValidationResult validateOwnerId(final String id) {

        if (TextUtils.isEmpty(id)) {
            return ValidationResultImpl.makeNullContent("Player name cannot be empty");
        } else {
            return ValidationResultImpl.makeValid();
        }

    }

    @Override
    public ValidationResult validate(final Score score) {

        if (score == null) {
            return ValidationResultImpl.makeNullContent("Null score");
        } else {
            final ValidationResult valueResult = validateValue(score.getValue());
            if (!valueResult.isValid()) {
                return valueResult;
            } else {
                final ValidationResult timeStampResult = validateTimeStamp(score.getTimeStamp());
                if (!timeStampResult.isValid()) {
                    return timeStampResult;
                } else {
                    final ValidationResult ownerIdResult = validateOwnerId(score.getOwnerId());
                    if (!ownerIdResult.isValid()) {
                        return ownerIdResult;
                    } else {
                        return new MascotValidator().validate(score.getMascot());
                    }
                }
            }
        }

    }
}
