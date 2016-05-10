package com.michaelfotiadis.crossyscore.ui.components.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewholder.BaseRecyclerViewHolder;

import butterknife.Bind;


public final class ScoreUiWrapperRecyclerViewHolder extends BaseRecyclerViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_two_lines_image;
    @Bind(R.id.line1)
    protected TextView title;
    @Bind(R.id.line2)
    protected TextView subTitle;
    @Bind(R.id.image)
    protected ImageView image;

    public ScoreUiWrapperRecyclerViewHolder(final View view) {
        super(view);
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}