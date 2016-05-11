package com.michaelfotiadis.crossyscore.ui.components.create.player;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;


public final class ListPlayerViewHolder extends BaseViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_three_lines_image;
    @Bind(R.id.line1)
    protected TextView title;
    @Bind(R.id.line2)
    protected TextView subTitle;
    @Bind(R.id.line3)
    protected TextView joinedText;
    @Bind(R.id.image)
    protected ImageView image;

    public ListPlayerViewHolder(final View view) {
        super(view);
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}