package com.michaelfotiadis.crossyscore.ui.core.intent.dispatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.intent.factory.IntentFactory;
import com.michaelfotiadis.crossyscore.ui.core.intent.factory.IntentFactoryImpl;


/**
 *
 */
public class IntentDispatcherImpl implements IntentDispatcher {

    private final Context mContext;
    private final IntentDispatcherInternal mDispatcher;
    private final IntentFactory mIntentFactory;

    public IntentDispatcherImpl(final Activity activity) {
        this.mIntentFactory = new IntentFactoryImpl(activity);
        this.mDispatcher = new IntentDispatcherInternal(activity);
        this.mContext = activity;
    }

    @Override
    public void dispatch(final View source, final Intent intent) {
        mDispatcher
                .withView(source)
                .withAnimation(ActivityAnimation.SCALE_UP_FROM_VIEW)
                .dispatch(intent);
    }

    @Override
    public void dispatchForResult(final View source, final Intent intent, final int requestCode) {
        mDispatcher
                .withView(source)
                .withAnimation(ActivityAnimation.SCALE_UP_FROM_VIEW)
                .forResult(requestCode)
                .dispatch(intent);
    }

    @Override
    public IntentFactory getIntentFactory() {
        return mIntentFactory;
    }

}