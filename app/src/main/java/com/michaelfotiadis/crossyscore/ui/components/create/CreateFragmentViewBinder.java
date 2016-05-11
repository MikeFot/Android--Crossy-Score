package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
public class CreateFragmentViewBinder extends BaseViewBinder<CreateFragmentViewHolder> {


    protected CreateFragmentViewBinder(final Activity activity,
                                       final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);

    }

    @Override
    public void bind(final CreateFragmentViewHolder holder) {
        ButterKnife.bind(holder, holder.getRoot());
    }

    @Override
    public void reset(final CreateFragmentViewHolder holder) {

    }
}
