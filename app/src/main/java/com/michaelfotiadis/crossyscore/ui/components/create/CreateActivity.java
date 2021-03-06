package com.michaelfotiadis.crossyscore.ui.components.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;

public class CreateActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = CreateActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, CreateActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);
        setTitle(R.string.title_create_score);
        addContentFragmentIfMissing(CreateFragment.newInstance(), FRAGMENT_TAG);

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG).onActivityResult(requestCode, resultCode, data);

    }
}
