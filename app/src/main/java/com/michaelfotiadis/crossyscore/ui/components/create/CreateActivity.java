package com.michaelfotiadis.crossyscore.ui.components.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.components.main.MainActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;

public class CreateActivity extends BaseActivity {

    private static final String FRAGMENT_TAG = CreateActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);

        addContentFragmentIfMissing(CreateFragment.newInstance(), FRAGMENT_TAG);

    }

}
