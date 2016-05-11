package com.michaelfotiadis.crossyscore.ui.core.common.viewholder;


import android.view.View;

/**
 * Base View Holder class
 */
public abstract class BaseViewHolder {

    private final View mRoot;

    public BaseViewHolder(final View view) {
        this.mRoot = view;
    }

    public View getRoot() {
        return mRoot;
    }

}
