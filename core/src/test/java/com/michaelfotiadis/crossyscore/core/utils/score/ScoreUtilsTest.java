package com.michaelfotiadis.crossyscore.core.utils.score;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.models.score.ScoreImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class ScoreUtilsTest {

    @Test
    public void testGetLatestScore() throws Exception {

        final List<Score> scoreList = new ArrayList();

        scoreList.add(ScoreImpl.newBuilder().withTimeStamp(1L).build());
        scoreList.add(ScoreImpl.newBuilder().withTimeStamp(2L).build());
        scoreList.add(ScoreImpl.newBuilder().withTimeStamp(3L).build());

        final Score score = ScoreUtils.getLatestScore(scoreList);
        assertNotNull(score);
        assertNotNull(score.getTimeStamp());
        assertEquals(3L, (long) score.getTimeStamp());

    }
}