package com.michaelfotiadis.crossyscore.ui.components.create;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class CreateFragmentViewHolder extends BaseViewHolder {

    @Bind(R.id.layout_mascot)
    protected CardView mascotLayout;
    @Bind(R.id.spinner_player)
    protected Spinner userSpinner;
    @Bind(R.id.button_add_player)
    protected Button addPlayerButton;
    @Bind(R.id.edit_text_score)
    protected EditText scoreText;
    @Bind(R.id.button_confirm)
    protected View confirmButton;

    public CreateFragmentViewHolder(final View view) {
        super(view);
    }
}
