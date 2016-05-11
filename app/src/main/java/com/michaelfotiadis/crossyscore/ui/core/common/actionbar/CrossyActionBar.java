package com.michaelfotiadis.crossyscore.ui.core.common.actionbar;

import android.view.View;

/**
 *
 */
public interface CrossyActionBar {

    View findViewById(final int id);

    void goTransparent();

    void hide();

    void setTitle(final CharSequence charSequence);

    void setTitle(final CharSequence charSequence, final int[] color);

    void setTitleAlpha(final float alpha);

    void show();
}
