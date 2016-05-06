package com.michaelfotiadis.crossyscore.ui.core.common.controller;

import android.app.Activity;
import android.view.View;

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

}
