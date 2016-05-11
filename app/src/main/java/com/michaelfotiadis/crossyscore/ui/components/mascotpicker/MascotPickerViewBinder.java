package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
public class MascotPickerViewBinder extends BaseViewBinder<MascotPickerViewHolder> {

    protected MascotPickerViewBinder(final Activity activity, final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
    }

    @Override
    public void bind(final MascotPickerViewHolder holder) {
        ButterKnife.bind(holder, holder.getRoot());
    }

    @Override
    public void reset(final MascotPickerViewHolder holder) {

    }
}
