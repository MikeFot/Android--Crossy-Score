package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.AppLog;

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

        holder.addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Adding new player");
                getIntentDispatcher().openAddPlayerActivity(v);
            }
        });

    }

    @Override
    public void reset(final CreateFragmentViewHolder holder) {

    }
}
