package com.michaelfotiadis.crossyscore.ui.components.create;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class CreateFragmentViewHolder extends BaseViewHolder {

    @Bind(R.id.spinner_mascot)
    protected Spinner mascotSpinner;
    @Bind(R.id.spinner_player)
    protected Spinner userSpinner;
    @Bind(R.id.button_add_player)
    protected Button addPlayerButton;

    public CreateFragmentViewHolder(final View view) {
        super(view);
    }
}
