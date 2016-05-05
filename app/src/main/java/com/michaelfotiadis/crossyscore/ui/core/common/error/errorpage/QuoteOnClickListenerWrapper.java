package com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage;

import android.view.View;

/**
 *
 */
public class QuoteOnClickListenerWrapper {

    private final View.OnClickListener mListener;
    private final int mResId;


    /**
     * Constructor which supports a resource id for the message to be displayed on the button
     *
     * @param resId    int resource ID of the message to be used
     * @param listener {@link View.OnClickListener} listener
     */
    public QuoteOnClickListenerWrapper(final int resId, final View.OnClickListener listener) {
        this.mResId = resId;
        this.mListener = listener;
    }

    public QuoteOnClickListenerWrapper(final View.OnClickListener listener) {
        this.mResId = 0;
        this.mListener = listener;
    }

    public View.OnClickListener getListener() {
        return mListener;
    }

    public int getResId() {
        return mResId;
    }
}
