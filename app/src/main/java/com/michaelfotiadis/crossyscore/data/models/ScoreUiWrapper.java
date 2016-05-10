package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;

/**
 *
 */
public interface ScoreUiWrapper extends AppModel, WithLongId {

    Creator<ScoreUiWrapper> CREATOR = new Creator<ScoreUiWrapper>() {
        @Override
        public ScoreUiWrapper createFromParcel(final Parcel source) {
            return new ScoreUiWrapperImpl(source);
        }

        @Override
        public ScoreUiWrapper[] newArray(final int size) {
            return new ScoreUiWrapper[size];
        }
    };

    Integer getValue();

    Integer getMascotResId();

    Integer getPlayerResId();

    String getMascotName();

    String getPlayerName();

    Long getTimeStamp();
}
