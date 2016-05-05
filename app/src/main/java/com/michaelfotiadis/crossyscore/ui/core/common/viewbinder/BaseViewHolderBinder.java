package com.michaelfotiadis.crossyscore.ui.core.common.viewbinder;

import android.content.Context;

import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

/**
 *
 */
public abstract class BaseViewHolderBinder<T extends BaseViewHolder> {

    private final Context mContext;
    private T mHolder;

    protected BaseViewHolderBinder(final Context context) {
        this.mContext = context;
    }

    public void bind(final T viewHolder) {
        mHolder = viewHolder;
    }

    public T getViewHolder() {
        return mHolder;
    }

    protected Context getContext() {
        return mContext;
    }

}
