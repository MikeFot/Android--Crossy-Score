package com.michaelfotiadis.crossyscore.ui.core.common.viewbinder;

import android.content.Context;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.view.ViewUtils;

/**
 *
 */
public abstract class BaseViewBinder<VH extends BaseViewHolder> {

    private final Context mContext;
    private final IntentDispatcher mIntentDispatcher;

    protected BaseViewBinder(final Context context, final IntentDispatcher intentDispatcher) {
        this.mContext = context;
        this.mIntentDispatcher = intentDispatcher;
    }

    public Context getContext() {
        return mContext;
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
