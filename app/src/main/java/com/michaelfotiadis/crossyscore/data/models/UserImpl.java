package com.michaelfotiadis.crossyscore.data.models;

import android.os.Parcel;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserImpl implements User {

    private final Player mPlayer;
    private final List<Score> mScores;

    private UserImpl(final Builder builder) {
        mPlayer = builder.player;
        mScores = builder.scores;
    }

    protected UserImpl(final Parcel in) {
        this.mPlayer = in.readParcelable(Player.class.getClassLoader());
        this.mScores = in.createTypedArrayList(Score.CREATOR);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(final UserImpl copy) {
        final Builder builder = new Builder();
        builder.player = copy.mPlayer;
        builder.scores = copy.mScores;
        return builder;
    }

    @Override
    public Score getTopScore() {
        return getTopScore(new DateTime(System.currentTimeMillis()));
    }

    @Override
    public Score getTopScore(final DateTime dateTime) {

        Score topScore = null;
        int topValue = 0;

        for (final Score score : getScores(dateTime)) {

            if (score.getValue() > topValue) {
                topScore = score;
                topValue = score.getValue();
            } else if (score.getValue() == topValue && topScore != null) {
                if (score.getTimeStamp() > topScore.getTimeStamp()) {
                    topScore = score;
                }
            }

        }

        return topScore;
    }

    @Override
    public List<Score> getScores(final DateTime dateTime) {

        final List<Score> requestedScores = new ArrayList<>();
        final DateTime todayStart = dateTime.withTimeAtStartOfDay();
        final DateTime tomorrowStart = dateTime.plusDays(1).withTimeAtStartOfDay();

        for (final Score score : mScores) {

            if (todayStart.isBefore(score.getTimeStamp()) && tomorrowStart.isAfter(score.getTimeStamp())) {
                requestedScores.add(score);
            }
        }

        return requestedScores;
    }

    @Override
    public Player getPlayer() {
        return mPlayer;
    }

    @Override
    public List<Score> getScores() {
        return mScores;
    }

    @Override
    public List<Score> getScoresToday() {
        return getScores(new DateTime(System.currentTimeMillis()));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(this.mPlayer, flags);
        dest.writeTypedList(mScores);
    }

    @Override
    public String getId() {
        if (getPlayer() != null) {
            return getPlayer().getId();
        } else {
            return "";
        }
    }

    public static final class Builder {

        private Player player;
        private List<Score> scores;

        private Builder() {
        }

        public Builder withPlayer(final Player val) {
            player = val;
            return this;
        }

        public Builder withScores(final List<Score> val) {
            scores = val;
            return this;
        }

        public User build() {
            return new UserImpl(this);
        }
    }
}
