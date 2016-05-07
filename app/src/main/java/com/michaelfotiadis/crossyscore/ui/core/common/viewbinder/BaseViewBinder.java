package com.michaelfotiadis.crossyscore.ui.core.common.viewbinder;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.view.ViewUtils;

/**
 *
 */
public abstract class BaseViewBinder<VH extends BaseViewHolder> {

    private final Activity mActivity;
    private final IntentDispatcher mIntentDispatcher;

    protected BaseViewBinder(final Activity activity, final IntentDispatcher intentDispatcher) {
        this.mActivity = activity;
        this.mIntentDispatcher = intentDispatcher;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    public abstract void bind(final VH holder);

    public abstract void reset(final VH holder);

    public void showView(final View view, final boolean show) {
        ViewUtils.showView(view, show);
    }

}
