package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.responses.CrossyCallback;
import com.michaelfotiadis.crossyscore.common.responses.CrossyDeliverable;
import com.michaelfotiadis.crossyscore.common.responses.CrossyError;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.factory.ScoreUiWrapperFactory;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.ScoreLoader;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
/*package*/ class MainFragmentController extends BaseController {

    private final MainFragmentViewBinder mBinder;
    private final RecyclerManager<ScoreUiWrapper> mRecyclerManager;
    private final ScoreUiWrapperFactory mFactory;

    public MainFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mFactory = new ScoreUiWrapperFactory(activity);

        mBinder = new MainFragmentViewBinder(activity, createIntentDispatcher());

        mBinder.bind(new MainFragmentViewHolder(view));

        mRecyclerManager = mBinder.getRecyclerManager(view);

        if (mRecyclerManager == null) {
            throw new NullPointerException("Recycler manager cannot be null");
        }

    }

    public void loadData() {
        AppLog.d("Loading data");
        final DataFeedLoaderAbstract<Score> scoreLoader = new ScoreLoader(getActivity());

        scoreLoader.setCallback(new DataFeedLoaderCallback<Score>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load scores: " + error);
            }

            @Override
            public void onSuccess(final List<Score> result) {

                mFactory.createScoreUiWrappers(result, new CrossyCallback<List<ScoreUiWrapper>>() {
                    @Override
                    public void onFailure(final CrossyError error) {
                        mRecyclerManager.setError(error.getErrorMessage());
                    }

                    @Override
                    public void onSuccess(final CrossyDeliverable<List<ScoreUiWrapper>> deliverable) {
                        if (deliverable.getContent().size() == 0) {
                            mRecyclerManager.setError("Nothing to see here yet");
                        } else {
                            mRecyclerManager.clearError();
                            mRecyclerManager.setItems(deliverable.getContent());
                            AppLog.d("Recycler has items: " + mRecyclerManager.getItemCount());
                        }
                    }
                });
            }
        });
        scoreLoader.loadData();
    }

    public void saveScore(final Score score) {
        if (score != null) {
            AppLog.d("Created score " + score);
            CrossyCore.getDataProvider().getScores().upsert(score);
            loadData();
        } else {
            AppLog.e("Score was null");
        }
    }

}
