package com.michaelfotiadis.crossyscore.common.models.score;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;

/**
 *
 */
public interface Score extends AppModel, WithLongId {

    Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(final Parcel source) {
            return new ScoreImpl(source);
        }

        @Override
        public Score[] newArray(final int size) {
            return new Score[size];
        }
    };

    String getOwnerId();

    Long getTimeStamp();

    Integer getValue();

    Mascot getMascot();
}
