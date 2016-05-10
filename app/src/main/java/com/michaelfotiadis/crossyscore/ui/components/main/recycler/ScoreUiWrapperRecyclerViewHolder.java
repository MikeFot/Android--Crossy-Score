package com.michaelfotiadis.crossyscore.ui.components.main.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewholder.BaseRecyclerViewHolder;

import butterknife.Bind;


public final class ScoreUiWrapperRecyclerViewHolder extends BaseRecyclerViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_score_wrapper;
    @Bind(R.id.score_text)
    protected TextView scoreText;
    @Bind(R.id.text_player)
    protected TextView playerName;
    @Bind(R.id.text_mascot)
    protected TextView mascotName;
    @Bind(R.id.text_when)
    protected TextView timeStamp;
    @Bind(R.id.image)
    protected ImageView imageAvatar;
    @Bind(R.id.image_mascot)
    protected ImageView imageMascot;

    public ScoreUiWrapperRecyclerViewHolder(final View view) {
        super(view);
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}