package com.michaelfotiadis.crossyscore.ui.components.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class MainFragmentViewHolder extends BaseViewHolder {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.fab_add)
    FloatingActionButton fab;

    public MainFragmentViewHolder(final View view) {
        super(view);
    }

    public FloatingActionButton getFab() {
        return fab;
    }
}
