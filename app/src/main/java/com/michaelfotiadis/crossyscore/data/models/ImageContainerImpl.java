package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

/**
 *
 */
public class ImageContainerImpl implements ImageContainer {

    private final Integer mResId;
    private final Integer mTintColor;

    private ImageContainerImpl(final Builder builder) {
        mResId = builder.mResId;
        mTintColor = builder.mTintColor;
    }

    protected ImageContainerImpl(final Parcel in) {
        this.mResId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mTintColor = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final ImageContainerImpl copy) {
        final Builder builder = new Builder();
        builder.mResId = copy.mResId;
        builder.mTintColor = copy.mTintColor;
        return builder;
    }

    @Override
    public Integer getResId() {
        return mResId;
    }

    @Override
    public Integer getTintColor() {
        return mTintColor;
    }

    @Override
    public Long getId() {
        return Long.valueOf(mResId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeValue(this.mResId);
        dest.writeValue(this.mTintColor);
    }

    public static final class Builder {

        private Integer mResId;
        private Integer mTintColor;

        private Builder() {
        }

        public Builder withResId(final Integer val) {
            mResId = val;
            return this;
        }

        public Builder withTintColor(final Integer val) {
            mTintColor = val;
            return this;
        }

        public ImageContainer build() {
            return new ImageContainerImpl(this);
        }
    }

}
