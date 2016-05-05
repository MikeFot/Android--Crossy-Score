package com.michaelfotiadis.crossyscore.ui.components.create;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class CreateViewHolder extends BaseViewHolder {

    @Bind(R.id.recycler_view)
    protected RecyclerView mMascotLayout;

    public CreateViewHolder(final View view) {
        super(view);
    }

    public RecyclerView getMascotRecyclerLayout() {
        return mMascotLayout;
    }
}
