package com.michaelfotiadis.crossyscore.data.statekeeper;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.models.score.ScoreImpl;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;
import com.michaelfotiadis.crossyscore.data.validation.validators.ScoreValidator;

/**
 *
 */
public class ScoreStateKeeper implements StateKeeper<Score> {

    private Mascot mascot;
    private String ownerId;
    private Integer value;

    public Mascot getMascot() {
        return mascot;
    }

    public void setMascot(final Mascot mascot) {
        this.mascot = mascot;
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
                .withMascot(mascot)
                .build();
    }

    private boolean isValid() {
        return validate().isValid();
    }

    public ValidationResult validate() {
        return new ScoreValidator().validate(buildInternal());
    }
}
