package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

/**
 *
 */
public class ScoreUiWrapperImpl implements ScoreUiWrapper {

    private final Integer value;
    private final Integer mascotResId;
    private final Integer playerResId;
    private final String mascotName;
    private final String playerName;
    private final Long timeStamp;

    private ScoreUiWrapperImpl(final Builder builder) {
        value = builder.value;
        mascotResId = builder.mascotResId;
        playerResId = builder.playerResId;
        mascotName = builder.mascotName;
        playerName = builder.playerName;
        timeStamp = builder.timeStamp;
    }

    protected ScoreUiWrapperImpl(final Parcel in) {
        this.value = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mascotResId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.playerResId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mascotName = in.readString();
        this.playerName = in.readString();
        this.timeStamp = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final ScoreUiWrapperImpl copy) {
        final Builder builder = new Builder();
        builder.value = copy.value;
        builder.mascotResId = copy.mascotResId;
        builder.playerResId = copy.playerResId;
        builder.mascotName = copy.mascotName;
        builder.playerName = copy.playerName;
        builder.timeStamp = copy.timeStamp;
        return builder;
    }


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Integer getMascotResId() {
        return mascotResId;
    }

    @Override
    public Integer getPlayerResId() {
        return playerResId;
    }

    @Override
    public String getMascotName() {
        return mascotName;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public Long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeValue(this.value);
        dest.writeValue(this.mascotResId);
        dest.writeValue(this.playerResId);
        dest.writeString(this.mascotName);
        dest.writeString(this.playerName);
        dest.writeValue(this.timeStamp);
    }

    @Override
    public Long getId() {
        return getTimeStamp();
    }

    public static final class Builder {

        private Integer value;
        private Integer mascotResId;
        private Integer playerResId;
        private String mascotName;
        private String playerName;
        private Long timeStamp;

        private Builder() {
        }

        public Builder withValue(final Integer val) {
            value = val;
            return this;
        }

        public Builder withMascotResId(final Integer val) {
            mascotResId = val;
            return this;
        }

        public Builder withPlayerResId(final Integer val) {
            playerResId = val;
            return this;
        }

        public Builder withMascotName(final String val) {
            mascotName = val;
            return this;
        }

        public Builder withPlayerName(final String val) {
            playerName = val;
            return this;
        }

        public Builder withTimeStamp(final Long val) {
            timeStamp = val;
            return this;
        }

        public ScoreUiWrapper build() {
            return new ScoreUiWrapperImpl(this);
        }
    }
}
