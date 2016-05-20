package com.michaelfotiadis.crossyscore.ui.components.score.recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

/**
 *
 */
public class ScoreUiWrapperRecyclerViewAdapter extends BaseRecyclerViewAdapter<ScoreUiWrapper, ScoreUiWrapperRecyclerViewHolder> {

    private final ScoreUiWrapperRecyclerBinder mBinder;

    public ScoreUiWrapperRecyclerViewAdapter(final Activity activity, final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
        mBinder = new ScoreUiWrapperRecyclerBinder(activity, intentDispatcher);
    }

    @Override
    protected boolean isItemValid(final ScoreUiWrapper item) {
        return item != null && item.getId() != null;
    }

    @Override
    public ScoreUiWrapperRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(ScoreUiWrapperRecyclerViewHolder.getLayoutId(), parent, false);

        return new ScoreUiWrapperRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScoreUiWrapperRecyclerViewHolder holder,
                                 final int position) {

        final ScoreUiWrapper item = getItem(position);

        mBinder.bind(holder, item);

    }

}
