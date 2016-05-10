package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;

/**
 *
 */
/*package*/ class MainFragmentController extends BaseController {

    private final MainFragmentViewHolder mHolder;
    private final RecyclerManager<ScoreUiWrapper> mRecyclerManager;

    public MainFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new MainFragmentViewHolder(view);

        mHolder.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                createIntentDispatcher().openCreateActivity(mHolder.getFab());
            }
        });

        // initialise the adapter
        final ScoreUiWrapperRecyclerViewAdapter adapter = new ScoreUiWrapperRecyclerViewAdapter(getActivity(), createIntentDispatcher());

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(
                view,
                mHolder.recyclerView);

        mRecyclerManager = new RecyclerManager.Builder<>(adapter)
                .setRecycler(mHolder.recyclerView)
                .setStateKeeper(uiStateKeeper)
                .setEmptyMessage(null)
                .build();

    }

}
