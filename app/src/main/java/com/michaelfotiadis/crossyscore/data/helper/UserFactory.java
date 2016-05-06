package com.michaelfotiadis.crossyscore.data.helper;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.data.models.UserImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserFactory {

    private final List<Player> mPlayers;
    private final List<Score> mScores;


    public UserFactory(final List<Player> players, final List<Score> scores) {
        this.mPlayers = players;
        this.mScores = scores;
    }

    public List<User> createUsers() {

        final List<User> users = new ArrayList<>();

        for (final Player player : mPlayers) {

            final List<Score> playerScores = ScoreUtils.getScoresForPlayer(player.getId(), mScores);

            users.add(
                    UserImpl.newBuilder()
                            .withPlayer(player)
                            .withScores(playerScores)
                            .build()
            );


        }

        return users;

    }


}
