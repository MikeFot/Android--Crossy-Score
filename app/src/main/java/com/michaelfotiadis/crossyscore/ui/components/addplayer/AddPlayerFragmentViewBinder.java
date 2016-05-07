package com.michaelfotiadis.crossyscore.ui.components.addplayer;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
public class AddPlayerFragmentViewBinder extends BaseViewBinder<AddPlayerFragmentViewHolder> {

    AddPlayerFragmentViewHolder mHolder;

    protected AddPlayerFragmentViewBinder(final Activity activity,
                                          final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
    }

    public void setOnClickListener(final View.OnClickListener listener) {
        mHolder.confirmButton.setOnClickListener(listener);
    }

    @Override
    public void bind(final AddPlayerFragmentViewHolder holder) {
        mHolder = holder;
        ButterKnife.bind(holder, holder.getView());
    }

    @Override
    public void reset(final AddPlayerFragmentViewHolder holder) {

    }
}
