package com.michaelfotiadis.crossyscore.ui.components.create;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewholder.BaseRecyclerViewHolder;


public final class MascotViewHolder extends BaseRecyclerViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_mascot;
    private final TextView desc;
    private final ImageView image;
    private final TextView label;

    public MascotViewHolder(final View view) {
        super(view);
        this.label = (TextView) view.findViewById(R.id.line1);
        this.desc = (TextView) view.findViewById(R.id.line2);
        this.image = (ImageView) view.findViewById(R.id.image);
    }

    public TextView getDesc() {
        return desc;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getLabel() {
        return label;
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}