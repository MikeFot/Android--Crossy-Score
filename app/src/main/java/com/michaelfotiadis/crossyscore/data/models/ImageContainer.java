package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;

/**
 *
 */
public interface ImageContainer extends AppModel, WithLongId {

    Creator<ImageContainer> CREATOR = new Creator<ImageContainer>() {
        @Override
        public ImageContainer createFromParcel(final Parcel source) {
            return new ImageContainerImpl(source);
        }

        @Override
        public ImageContainer[] newArray(final int size) {
            return new ImageContainer[size];
        }
    };

    Integer getResId();

    Integer getTintColor();
}
