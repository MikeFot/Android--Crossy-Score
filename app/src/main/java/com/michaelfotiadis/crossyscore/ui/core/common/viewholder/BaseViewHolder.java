package com.michaelfotiadis.crossyscore.ui.core.common.viewholder;


import android.view.View;

/**
 * Base View Holder class
 */
public abstract class BaseViewHolder {

    private final View mView;

    public BaseViewHolder(final View view) {
        this.mView = view;
    }

    public View getView() {
        return mView;
    }

}
