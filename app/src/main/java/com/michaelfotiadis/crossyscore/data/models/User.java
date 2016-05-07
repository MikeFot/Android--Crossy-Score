package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithStringId;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;

import org.joda.time.DateTime;

import java.util.List;

/**
 *
 */
public interface User extends AppModel, WithStringId {

    Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(final Parcel source) {
            return new UserImpl(source);
        }

        @Override
        public User[] newArray(final int size) {
            return new User[size];
        }
    };

    Score getTopScore();

    Score getTopScore(DateTime dateTime);

    List<Score> getScores(DateTime dateTime);

    Player getPlayer();

    List<Score> getScores();

    List<Score> getScoresToday();
}
