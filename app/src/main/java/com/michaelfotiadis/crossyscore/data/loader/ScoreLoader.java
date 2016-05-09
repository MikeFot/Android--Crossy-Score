package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class ScoreLoader extends DataFeedLoaderAbstract<Score> {

    public ScoreLoader(final Activity activity) {
        super(activity);
    }

    @Override
    public void loadData() {

        final List<Score> scores = CrossyCore.getDataProvider().getScores().getAll();

        if (scores == null) {
            notifyError(new UiDataLoadError("Null scores found", UiDataLoadError.ErrorKind.NO_DATA, false));
        } else {
            AppLog.d("Loaded " + scores.size() + " scores");
            notifySuccess(scores);
        }

    }
}
