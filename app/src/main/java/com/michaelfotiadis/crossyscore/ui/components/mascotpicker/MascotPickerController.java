package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.MascotLoader;
import com.michaelfotiadis.crossyscore.ui.components.mascotpicker.recycler.MascotPickerRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.search.DataFilter;
import com.michaelfotiadis.crossyscore.ui.core.common.search.FilterFinishedCallback;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class MascotPickerController extends BaseController {

    private final MascotPickerViewBinder mBinder;
    private final MascotPickerViewHolder mHolder;
    private final RecyclerManager<Mascot> mRecyclerManager;

    protected MascotSearcher mSearcher;

    public MascotPickerController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new MascotPickerViewHolder(view);

        mBinder = new MascotPickerViewBinder(getActivity(), createIntentDispatcher());
        mBinder.bind(mHolder);

        mHolder.recyclerView.setHasFixedSize(true);

        // call the utilities to get the appropriate layout manager
        mHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // initialise the adapter
        final MascotPickerRecyclerViewAdapter adapter = new MascotPickerRecyclerViewAdapter(
                getActivity(),
                createIntentDispatcher());

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(
                view,
                mHolder.recyclerView);

        mRecyclerManager = new RecyclerManager.Builder<>(adapter)
                .setRecycler(mHolder.recyclerView)
                .setStateKeeper(uiStateKeeper)
                .setEmptyMessage(null)
                .build();

    }

    protected void loadMascots() {
        final DataFeedLoaderAbstract<Mascot> mascotLoader = new MascotLoader(getActivity());
        mRecyclerManager.updateUiState();
        mascotLoader.setCallback(new DataFeedLoaderCallback<Mascot>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load mascots: " + error);
                mRecyclerManager.setError(error.getMessage());
            }

            @Override
            public void onSuccess(final List<Mascot> mascots) {

                mSearcher = new MascotSearcher(mascots);
                mSearcher.setEmptyQueryBehaviour(DataFilter.EmptyQueryBehaviour.SHOW_ALL);
                mSearcher.filter(null, new FilterFinishedCallback<Mascot>() {
                    @Override
                    public void onSearchFinished(final List<Mascot> results) {
                        mRecyclerManager.clearError();
                        mRecyclerManager.setItems(results);
                    }
                });
            }
        });

        mascotLoader.loadData();
    }

    public void setFilter(final String filter) {
        AppLog.d("Search parameters: " + filter);
        if (mSearcher != null) {
            mSearcher.filter(filter, new FilterFinishedCallback<Mascot>() {
                @Override
                public void onSearchFinished(final List<Mascot> results) {
                    mRecyclerManager.setItems(results);
                }
            });
        }
    }

}
