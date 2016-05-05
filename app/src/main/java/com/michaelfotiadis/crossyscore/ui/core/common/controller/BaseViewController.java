package com.michaelfotiadis.crossyscore.ui.core.common.controller;

import android.content.Context;
import android.view.View;

/**
 *
 */
public abstract class BaseViewController {

    private final Context mContext;
    private final View mView;

    public BaseViewController(final Context context, final View view) {
        this.mContext = context;
        this.mView = view;
    }

    public View getView() {
        return mView;
    }

    public Context getContext() {
        return mContext;
    }
}
