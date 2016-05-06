package com.michaelfotiadis.crossyscore.common.models.player;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class PlayerImpl implements Player {

    @SerializedName("name")
    private final String name;
    @SerializedName("alias")
    private final String alias;
    @SerializedName("registeredOn")
    private final Long registeredOn;
    @SerializedName("drawableResId")
    private final Integer drawableResId;

    private PlayerImpl(final Builder builder) {
        name = builder.name;
        alias = builder.alias;
        registeredOn = builder.registeredOn;
        drawableResId = builder.drawableResId;
    }

    protected PlayerImpl(final Parcel in) {
        this.name = in.readString();
        this.alias = in.readString();
        this.registeredOn = (Long) in.readValue(Long.class.getClassLoader());
        this.drawableResId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final PlayerImpl copy) {
        final Builder builder = new Builder();
        builder.name = copy.name;
        builder.alias = copy.alias;
        builder.registeredOn = copy.registeredOn;
        builder.drawableResId = copy.drawableResId;
        return builder;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public Integer getDrawableResId() {
        return drawableResId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getRegisteredOn() {
        return registeredOn;
    }

    @Override
    public String getId() {
        return getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.name);
        dest.writeString(this.alias);
        dest.writeValue(this.registeredOn);
        dest.writeValue(this.drawableResId);
    }

    public static final class Builder {

        private String name;
        private String alias;
        private Long registeredOn;
        private Integer drawableResId;

        private Builder() {
        }

        public Builder withName(final String val) {
            name = val;
            return this;
        }

        public Builder withAlias(final String val) {
            alias = val;
            return this;
        }

        public Builder withRegisteredOn(final Long val) {
            registeredOn = val;
            return this;
        }

        public Builder withDrawableResId(final Integer val) {
            drawableResId = val;
            return this;
        }

        public Player build() {
            return new PlayerImpl(this);
        }
    }


}
