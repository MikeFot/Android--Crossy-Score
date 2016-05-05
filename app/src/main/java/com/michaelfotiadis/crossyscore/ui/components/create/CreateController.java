package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseViewController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;

import java.util.List;

import butterknife.ButterKnife;

/**
 *
 */
public class CreateController extends BaseViewController {

    private final CreateViewHolder mHolder;
    private final CreateViewBinder mBinder;

    private final RecyclerManager<Mascot> mMascotRecyclerManager;

    public CreateController(final Context context, final View view) {
        super(context, view);

        mHolder = new CreateViewHolder(view);
        ButterKnife.bind(mHolder, view);
        mBinder = new CreateViewBinder(context);

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(view, mHolder.getMascotRecyclerLayout());
        mHolder.getMascotRecyclerLayout().setHasFixedSize(true);
        mHolder.getMascotRecyclerLayout().setLayoutManager(new LinearLayoutManager(context));
        mMascotRecyclerManager = new RecyclerManager.Builder<>(new RecyclerViewAdapter((Activity) context))
                .setRecycler(mHolder.getMascotRecyclerLayout())
                .setStateKeeper(uiStateKeeper)
                .setEmptyMessage(null)
                .build();
    }

    public void setData(final List<Mascot> items) {
        mMascotRecyclerManager.addItems(items);
    }

}
