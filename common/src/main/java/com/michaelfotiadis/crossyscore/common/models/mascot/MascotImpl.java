package com.michaelfotiadis.crossyscore.common.models.mascot;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class MascotImpl implements Mascot {

    @SerializedName("id")
    private final Long id;
    @SerializedName("name")
    private final String name;
    @SerializedName("release")
    private final String release;
    @SerializedName("resId")
    private final Integer resId;

    private MascotImpl(final Builder builder) {
        id = builder.id;
        name = builder.name;
        release = builder.release;
        resId = builder.resId;
    }

    protected MascotImpl(final Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.release = in.readString();
        this.resId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final MascotImpl copy) {
        final Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.release = copy.release;
        builder.resId = copy.resId;
        return builder;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRelease() {
        return release;
    }

    @Override
    public Integer getResId() {
        return resId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.release);
        dest.writeValue(this.resId);
    }

    public static final class Builder {

        private Long id;
        private String name;
        private String release;
        private Integer resId;

        private Builder() {
        }

        public Builder withId(final Long val) {
            id = val;
            return this;
        }

        public Builder withName(final String val) {
            name = val;
            return this;
        }

        public Builder withRelease(final String val) {
            release = val;
            return this;
        }

        public Builder withResId(final Integer val) {
            resId = val;
            return this;
        }

        public Mascot build() {
            return new MascotImpl(this);
        }
    }

    @Override
    public String toString() {
        return "MascotImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release='" + release + '\'' +
                ", resId=" + resId +
                '}';
    }
}
