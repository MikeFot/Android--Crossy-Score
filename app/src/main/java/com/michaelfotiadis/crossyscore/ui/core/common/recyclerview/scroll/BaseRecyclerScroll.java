package com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.scroll;

import android.support.v7.widget.RecyclerView;

public abstract class BaseRecyclerScroll extends RecyclerView.OnScrollListener {

    static final float MINIMUM = 25;
    int scrollDist = 0;
    boolean isVisible = true;

    public abstract void show();

    public abstract void hide();

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (isVisible && scrollDist > MINIMUM) {
            hide();
            scrollDist = 0;
            isVisible = false;
        } else if (!isVisible && scrollDist < -MINIMUM) {
            show();
            scrollDist = 0;
            isVisible = true;
        }
        if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
            scrollDist += dy;
        }
    }
}