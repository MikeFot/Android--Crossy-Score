package com.michaelfotiadis.crossyscore.ui.components.addplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;

public class AddPlayerActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = AddPlayerActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, AddPlayerActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);
        addContentFragmentIfMissing(AddPlayerFragment.newInstance(), FRAGMENT_TAG);
    }
}
