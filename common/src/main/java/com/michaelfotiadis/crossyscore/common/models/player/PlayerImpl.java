package com.michaelfotiadis.crossyscore.common.models.player;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.michaelfotiadis.crossyscore.common.models.score.Score;

import java.util.List;

/**
 *
 */
public class PlayerImpl implements Player {

    @SerializedName("name")
    private final String name;
    @SerializedName("registeredOn")
    private final Long registeredOn;
    @SerializedName("scores")
    private final List<Score> scores;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getRegisteredOn() {
        return registeredOn;
    }

    @Override
    public List<Score> getScores() {
        return scores;
    }

    private PlayerImpl(final Builder builder) {
        name = builder.name;
        registeredOn = builder.registeredOn;
        scores = builder.scores;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final PlayerImpl copy) {
        final Builder builder = new Builder();
        builder.name = copy.name;
        builder.registeredOn = copy.registeredOn;
        builder.scores = copy.scores;
        return builder;
    }

    @Override
    public String getId() {
        return getName();
    }


    public static final class Builder {

        private String name;
        private Long registeredOn;
        private List<Score> scores;

        private Builder() {
        }

        public Builder withName(final String val) {
            name = val;
            return this;
        }

        public Builder withRegisteredOn(final Long val) {
            registeredOn = val;
            return this;
        }

        public Builder withScores(final List<Score> val) {
            scores = val;
            return this;
        }

        public Player build() {
            return new PlayerImpl(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.registeredOn);
        dest.writeTypedList(scores);
    }

    protected PlayerImpl(final Parcel in) {
        this.name = in.readString();
        this.registeredOn = (Long) in.readValue(Long.class.getClassLoader());
        this.scores = in.createTypedArrayList(Score.CREATOR);
    }

}
