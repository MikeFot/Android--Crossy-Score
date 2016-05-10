package com.michaelfotiadis.crossyscore.ui.components.scoredetails;

import android.os.Bundle;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;

/**
 *
 */
public class ScoreDetailsFragment extends BaseFragment {

    private static final String EXTRA = ScoreDetailsFragment.class.getSimpleName() + "_extra_1";

    private Score mScore;

    public static BaseFragment newInstance(final ScoreUiWrapper score) {

        final BaseFragment fragment = new ScoreDetailsFragment();

        final Bundle args = new Bundle();
        args.putParcelable(EXTRA, score);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mScore = getArguments().getParcelable(EXTRA);
        }

    }

}
