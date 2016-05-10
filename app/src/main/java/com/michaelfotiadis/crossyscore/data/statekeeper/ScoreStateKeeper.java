package com.michaelfotiadis.crossyscore.data.statekeeper;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.models.score.ScoreImpl;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;
import com.michaelfotiadis.crossyscore.data.validation.validators.ScoreValidator;

/**
 *
 */
public class ScoreStateKeeper implements StateKeeper<Score> {

    private String mascotId;
    private String ownerId;
    private Integer value;

    public String getMascotId() {
        return mascotId;
    }

    public void setMascotId(final String mascotId) {
        this.mascotId = mascotId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

    @Override
    public boolean isReady() {
        return getState().equals(State.READY);
    }

    @Override
    public State getState() {
        if (isValid()) {
            return State.READY;
        } else {
            return State.NOT_READY;
        }
    }

    @Override
    public Score build() {
        if (isValid()) {
            return buildInternal();
        } else {
            return null;
        }
    }

    private Score buildInternal() {
        return ScoreImpl.newBuilder()
                .withValue(value)
                .withTimeStamp(System.currentTimeMillis())
                .withOwnerId(ownerId)
                .withMascotId(mascotId)
                .build();
    }

    private boolean isValid() {
        return validate().isValid();
    }

    public ValidationResult validate() {
        return new ScoreValidator().validate(buildInternal());
    }
}
