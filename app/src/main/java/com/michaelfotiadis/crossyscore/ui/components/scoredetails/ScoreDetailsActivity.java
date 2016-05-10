package com.michaelfotiadis.crossyscore.ui.components.scoredetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;

public class ScoreDetailsActivity extends BaseActivity {

    private static final String FRAGMENT_TAG =
            ScoreDetailsActivity.class.getSimpleName() + "_fragment_tag";

    private static final String EXTRA = ScoreDetailsActivity.class.getSimpleName() + "_extra_1";

    public static Intent getInstance(final Context context, final ScoreUiWrapper score) {

        final Intent intent = new Intent(context, ScoreDetailsActivity.class);
        intent.putExtra(EXTRA, score);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_default_fragment_container);

        if (savedInstanceState != null) {

            final Bundle extras = getIntent().getExtras();
            if (extras != null) {
                final ScoreUiWrapper score = extras.getParcelable(EXTRA);
                if (score != null) {
                    addContentFragmentIfMissing(ScoreDetailsFragment.newInstance(score), FRAGMENT_TAG);
                }
            }
        }

    }
}
