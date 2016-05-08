package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
/*package*/ class MainFragmentController extends BaseController {

    private final MainFragmentViewHolder mHolder;

    public MainFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new MainFragmentViewHolder(view);
        ButterKnife.bind(mHolder, view);


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
