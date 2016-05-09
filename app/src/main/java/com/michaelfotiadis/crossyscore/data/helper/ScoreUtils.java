package com.michaelfotiadis.crossyscore.data.helper;

import com.michaelfotiadis.crossyscore.common.models.score.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public final class ScoreUtils {

    private ScoreUtils() {
        // NOOP
    }

    public static Score getLatestScore(final List<Score> scores) {
        if (scores.size() > 0) {
            Collections.sort(scores, new ScoreDateComparator());
            return scores.get(0);
        } else {
            return null;
        }
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

    protected static class ScoreDateComparator implements Comparator<Score> {

        @Override
        public int compare(final Score lhs, final Score rhs) {
            if (lhs.getTimeStamp() < rhs.getTimeStamp()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
