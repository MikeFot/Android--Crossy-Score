package com.michaelfotiadis.crossyscore.common.models.mascot;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;

/**
 *
 */
public interface Mascot extends AppModel, WithLongId {

    Creator<Mascot> CREATOR = new Creator<Mascot>() {
        @Override
        public Mascot createFromParcel(final Parcel source) {
            return new MascotImpl(source);
        }

        @Override
        public Mascot[] newArray(final int size) {
            return new Mascot[size];
        }
    };

    @Override
    Long getId();

    String getName();

    String getRelease();

    Integer getResId();
}
