package com.michaelfotiadis.crossyscore.ui.core.common.anim;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;


/**
 *
 */
public class AnimationHelper {

    private final Context mContext;

    public AnimationHelper(final Context context) {
        this.mContext = context;
    }

    public Bundle getCustomAnimationBundle(final int animationIn, final int animationOut) {
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(mContext, animationIn, animationOut);
        return activityOptionsCompat.toBundle();
    }

    public Bundle getScaleUpBundle(final View view) {
        return getScaleUpBundle(view, view.getWidth(), view.getHeight());
    }

    @SuppressWarnings("MethodMayBeStatic")
    public Bundle getScaleUpBundle(final View view, final int width, final int height) {
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, width, height);
        return activityOptionsCompat.toBundle();
    }

    public Bundle getSlideInFromLeftBundle() {
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(mContext, R.anim.slide_in_right, R.anim.zoom_out);
        return activityOptionsCompat.toBundle();
    }

    public Bundle getSlideUpBundle() {
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(mContext, R.anim.slide_up_bottom, R.anim.zoom_out);
        return activityOptionsCompat.toBundle();
    }
}
