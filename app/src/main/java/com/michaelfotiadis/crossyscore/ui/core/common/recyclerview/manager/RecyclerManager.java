package com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager;

import android.support.v7.widget.RecyclerView;

import com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage.QuoteOnClickListenerWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.animation.ItemAnimator;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public final class RecyclerManager<D> {

    private final BaseRecyclerViewAdapter<D, ?> adapter;
    private final StateCoordinator stateCoordinator;

    private RecyclerManager(final Builder<D> builder) {
        this.stateCoordinator = new StateCoordinator(
                builder.adapter,
                builder.stateKeeper,
                builder.emptyMessage);

        this.adapter = builder.adapter;
        this.adapter.setOnItemsChangedListener(
                new BaseRecyclerViewAdapter.OnItemsChangedListener() {
                    @Override
                    public void onItemsChanged() {
                        updateUiState();
                    }
                });

        if (builder.animator == null) {
            builder.recycler.setItemAnimator(new ItemAnimator());
        } else {
            builder.recycler.setItemAnimator(builder.animator);
        }

        builder.recycler.setAdapter(adapter);
    }

    public void addItem(final D item, final int position) {
        adapter.addItem(item, position);
    }

    public void addItem(final D item) {
        adapter.addItem(item);
    }

    public void addItems(final Collection<D> items) {
        adapter.addItems(items);
    }

    public void clearError() {
        stateCoordinator.clearError();
    }

    public void clearItems() {
        adapter.clearItems();
    }

    public int getItemCount() {
        return adapter.getItemCount();
    }

    public boolean hasHadDataAdded() {
        return adapter.hasAttemptedDataAddition();
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    public void removeItem(final int position) {
        adapter.removeItem(position);
    }

    public void removeItem(final D item) {
        adapter.removeItem(item);
    }

    public void setError(final CharSequence errorMessage) {
        setError(errorMessage, null);
    }

    public void setError(final CharSequence errorMessage,
                         final QuoteOnClickListenerWrapper listenerWrapper) {
        stateCoordinator.setError(errorMessage, listenerWrapper);
    }

    public void setItems(final List<D> items) {
        adapter.setItems(items);
    }

    public void updateUiState() {
        stateCoordinator.updateUiState();
    }

    public static class Builder<D> {

        private final BaseRecyclerViewAdapter<D, ?> adapter;
        private RecyclerView.ItemAnimator animator = new ItemAnimator();
        private CharSequence emptyMessage;
        private RecyclerView recycler;
        private UiStateKeeper stateKeeper;


        public Builder(final BaseRecyclerViewAdapter<D, ?> adapter) {
            this.adapter = adapter;
        }

        public RecyclerManager<D> build() {
            if (recycler == null) {
                throw new IllegalArgumentException("RecyclerView cannot be null");
            }

            if (adapter == null) {
                throw new IllegalArgumentException("RecyclerViewAdapter cannot be null");
            }

            return new RecyclerManager<>(this);
        }

        public Builder<D> setAnimator(final RecyclerView.ItemAnimator animator) {
            this.animator = animator;
            return this;
        }

        public Builder<D> setEmptyMessage(final CharSequence emptyMessage) {
            this.emptyMessage = emptyMessage;
            return this;
        }

        public Builder<D> setRecycler(final RecyclerView recycler) {
            this.recycler = recycler;
            return this;
        }

        public Builder<D> setStateKeeper(final UiStateKeeper stateKeeper) {
            this.stateKeeper = stateKeeper;
            return this;
        }
    }
}
