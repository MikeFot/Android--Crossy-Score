package com.michaelfotiadis.crossyscore.common.models.score;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;

/**
 *
 */
public class ScoreImpl implements Score {

    @SerializedName("timeStamp")
    private final Long timeStamp;
    @SerializedName("ownerId")
    private final String ownerId;
    @SerializedName("value")
    private final Integer value;
    @SerializedName("mascot")
    private final Mascot mascot;

    private ScoreImpl(final Builder builder) {
        timeStamp = builder.timeStamp;
        ownerId = builder.ownerId;
        value = builder.value;
        mascot = builder.mascot;
    }

    protected ScoreImpl(final Parcel in) {
        this.timeStamp = (Long) in.readValue(Long.class.getClassLoader());
        this.ownerId = in.readString();
        this.value = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mascot = in.readParcelable(Mascot.class.getClassLoader());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final ScoreImpl copy) {
        final Builder builder = new Builder();
        builder.timeStamp = copy.timeStamp;
        builder.ownerId = copy.ownerId;
        builder.value = copy.value;
        builder.mascot = copy.mascot;
        return builder;
    }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public Long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Mascot getMascot() {
        return mascot;
    }

    @Override
    public Long getId() {
        return getTimeStamp();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeValue(this.timeStamp);
        dest.writeString(this.ownerId);
        dest.writeValue(this.value);
        dest.writeParcelable(this.mascot, flags);
    }

    public static final class Builder {

        private Long timeStamp;
        private String ownerId;
        private Integer value;
        private Mascot mascot;

        private Builder() {
        }

        public Builder withTimeStamp(final Long val) {
            timeStamp = val;
            return this;
        }

        public Builder withOwnerId(final String val) {
            ownerId = val;
            return this;
        }

        public Builder withValue(final Integer val) {
            value = val;
            return this;
        }

        public Builder withMascot(final Mascot val) {
            mascot = val;
            return this;
        }

        public Score build() {
            return new ScoreImpl(this);
        }
    }
}
