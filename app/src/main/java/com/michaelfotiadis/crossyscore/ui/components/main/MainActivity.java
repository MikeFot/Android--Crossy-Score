package com.michaelfotiadis.crossyscore.ui.components.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = MainActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);

        addContentFragmentIfMissing(MainFragment.newInstance(), FRAGMENT_TAG);

    }
}