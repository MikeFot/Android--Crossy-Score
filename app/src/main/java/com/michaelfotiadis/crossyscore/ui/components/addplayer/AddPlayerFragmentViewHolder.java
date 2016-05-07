package com.michaelfotiadis.crossyscore.ui.components.addplayer;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class AddPlayerFragmentViewHolder extends BaseViewHolder {

    @Bind(R.id.text_name)
    protected EditText textName;
    @Bind(R.id.text_alias)
    protected EditText textAlias;
    @Bind(R.id.spinner_avatar)
    protected Spinner spinnerAvatar;
    @Bind(R.id.button_confirm)
    protected View confirmButton;

    public AddPlayerFragmentViewHolder(final View view) {
        super(view);
    }
}
