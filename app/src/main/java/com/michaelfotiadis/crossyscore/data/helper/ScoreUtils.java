package com.michaelfotiadis.crossyscore.data.helper;

import com.michaelfotiadis.crossyscore.common.models.score.Score;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class ScoreUtils {

    private ScoreUtils() {
        // NOOP
    }

    public static List<Score> getScoresForPlayer(final String id,
                                                 final List<Score> scores) {

        final List<Score> filteredScores = new ArrayList<>();

        for (final Score score : scores) {

            if (score.getOwnerId().equals(id)) {
                filteredScores.add(score);
            }

        }

        return filteredScores;
    }

}
