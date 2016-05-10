package com.michaelfotiadis.crossyscore.common.models.mascot;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithStringId;

/**
 *
 */
public interface Mascot extends AppModel, WithStringId {

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

    Long getOrder();

    String getName();

    String getRelease();

    Integer getResId();
}
