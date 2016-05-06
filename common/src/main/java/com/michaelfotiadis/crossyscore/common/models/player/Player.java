package com.michaelfotiadis.crossyscore.common.models.player;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithStringId;

/**
 *
 */
public interface Player extends AppModel, WithStringId {

    Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(final Parcel source) {
            return new PlayerImpl(source);
        }

        @Override
        public Player[] newArray(final int size) {
            return new Player[size];
        }
    };

    String getAlias();

    Integer getDrawableResId();

    String getName();

    Long getRegisteredOn();

}
