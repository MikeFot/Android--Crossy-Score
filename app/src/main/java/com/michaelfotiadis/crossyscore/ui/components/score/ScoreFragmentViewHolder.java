package com.michaelfotiadis.crossyscore.ui.components.score;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class ScoreFragmentViewHolder extends BaseViewHolder {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.fab_add)
    FloatingActionButton fab;

    public ScoreFragmentViewHolder(final View view) {
        super(view);
    }
}
