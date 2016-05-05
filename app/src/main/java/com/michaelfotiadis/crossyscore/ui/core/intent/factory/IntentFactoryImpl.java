package com.michaelfotiadis.crossyscore.ui.core.intent.factory;

import android.content.Context;
import android.content.Intent;

import com.michaelfotiadis.crossyscore.ui.components.create.CreateActivity;
import com.michaelfotiadis.crossyscore.ui.components.main.MainActivity;


/**
 *
 */
public class IntentFactoryImpl implements IntentFactory {

    private final Context mContext;

    public IntentFactoryImpl(final Context context) {
        mContext = context.getApplicationContext();
    }

    @Override
    public Intent getHomeIntent() {
        final Intent intent = MainActivity.getInstance(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    public Intent getCreateIntent() {
        return CreateActivity.getInstance(mContext);
    }


}
