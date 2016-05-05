package com.michaelfotiadis.crossyscore.utils.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 *
 */
/*package*/ final class ViewAnimator {

    private static final int DEFAULT_SPEED_FACTOR = 4;

    private ViewAnimator() {
    }

    private static int calcDuration(final View view, final int height, final int speedFactor) {
        // 1dp/ms for speedFactor==1
        return (int) (height / view.getContext().getResources().getDisplayMetrics().density) * speedFactor;
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        final Animation a = new Animation() {
            @Override
            protected void applyTransformation(final float interpolatedTime, final Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(calcDuration(v, initialHeight, DEFAULT_SPEED_FACTOR));
        v.startAnimation(a);
    }

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        final Animation a = new Animation() {
            @Override
            protected void applyTransformation(final float interpolatedTime, final Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(calcDuration(v, targetHeight, DEFAULT_SPEED_FACTOR));
        v.startAnimation(a);
    }
}
