package com.michaelfotiadis.crossyscore.ui.core.common.controller;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcherImpl;

/**
 *
 */
public abstract class BaseViewController {

    private final Activity mActivity;
    private final View mView;

    public BaseViewController(final Activity activity, final View view) {
        this.mActivity = activity;
        this.mView = view;
    }


    public View getView() {
        return mView;
    }

    public Activity getActivity() {
        return mActivity;
    }

    protected IntentDispatcher createIntentDispatcher() {
        if (mActivity instanceof BaseActivity) {
            return ((BaseActivity) mActivity).getIntentDispatcher();
        } else {
            return new IntentDispatcherImpl(mActivity);
        }
    }

}
