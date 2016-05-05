package com.michaelfotiadis.crossyscore.ui.components.main;

import android.content.Context;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseViewController;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
/*package*/ class MainFragmentController extends BaseViewController {

    private final MainFragmentViewHolder mHolder;
    private final MainFragmentViewBinder mBinder;

    public MainFragmentController(final Context context, final View view) {
        super(context, view);

        mHolder = new MainFragmentViewHolder(view);
        ButterKnife.bind(mHolder, view);

        mBinder = new MainFragmentViewBinder(context);
        mBinder.bind(mHolder);

    }

    public void init(final IntentDispatcher dispatcher) {

        mHolder.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                dispatcher.openCreateActivity(mHolder.getFab());
            }
        });

    }

}
