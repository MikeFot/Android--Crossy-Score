package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.MascotLoader;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

import butterknife.ButterKnife;

/**
 *
 */
public class MascotPickerController extends BaseController {

    private final MascotPickerViewHolder mHolder;
    private final RecyclerManager<Mascot> mRecyclerManager;

    public MascotPickerController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new MascotPickerViewHolder(view);
        ButterKnife.bind(this, view);

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(
                view,
                mHolder.recyclerView);

        mHolder.recyclerView.setHasFixedSize(true);

        // call the utilities to get the appropriate layout manager
        mHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // initialise the adapter
        final MascotPickerRecyclerViewAdapter adapter = new MascotPickerRecyclerViewAdapter(getActivity(), createIntentDispatcher());

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
                mRecyclerManager.setItems(mascots);
                mRecyclerManager.clearError();
            }
        });

        mascotLoader.loadData();
    }

}
