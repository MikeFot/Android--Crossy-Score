package com.michaelfotiadis.crossyscore.ui.components.addplayer.avatar;

import android.view.View;
import android.widget.ImageView;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;


public final class ListAvatarViewHolder extends BaseViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_single_image;
    @Bind(R.id.image)
    protected ImageView image;

    public ListAvatarViewHolder(final View view) {
        super(view);
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}