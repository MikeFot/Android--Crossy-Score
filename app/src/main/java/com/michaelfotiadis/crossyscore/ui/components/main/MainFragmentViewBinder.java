package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.components.main.recycler.ScoreUiWrapperRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.scroll.BaseRecyclerScrollListener;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
/*package*/ class MainFragmentViewBinder extends BaseViewBinder<MainFragmentViewHolder> {

    private MainFragmentViewHolder mHolder;

    protected MainFragmentViewBinder(final Activity activity, final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
    }

    @Override
    public void bind(final MainFragmentViewHolder holder) {

        mHolder = holder;

        ButterKnife.bind(holder, holder.getRoot());

        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.simple_grow);
        holder.fab.setAnimation(animation);
        holder.fab.animate();
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getIntentDispatcher().openCreateActivity(holder.fab);
            }
        });

        // call the utilities to get the appropriate layout manager
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        holder.recyclerView.addOnScrollListener(new FabRecyclerScrollListener(holder));
    }

    @Override
    public void reset(final MainFragmentViewHolder holder) {

    }

    public RecyclerManager<ScoreUiWrapper> getRecyclerManager(final View view) {
        // initialise the adapter
        final ScoreUiWrapperRecyclerViewAdapter adapter = new ScoreUiWrapperRecyclerViewAdapter(getActivity(), getIntentDispatcher());

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(
                view,
                mHolder.recyclerView);

        return new RecyclerManager.Builder<>(adapter)
                .setRecycler(mHolder.recyclerView)
                .setStateKeeper(uiStateKeeper)
                .setEmptyMessage(null)
                .build();
    }

    private static class FabRecyclerScrollListener extends BaseRecyclerScrollListener {

        private final MainFragmentViewHolder holder;

        public FabRecyclerScrollListener(final MainFragmentViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void show() {
            holder.fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
        }

        @Override
        public void hide() {
            holder.fab.animate().translationY(holder.fab.getHeight() + holder.fab.getHeight() / 2).setInterpolator(new AccelerateInterpolator(2)).start();
        }
    }
}
