package com.michaelfotiadis.crossyscore.ui.core.intent.dispatch;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.anim.AnimationHelper;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.AppToast;
import com.michaelfotiadis.crossyscore.ui.core.intent.NavLog;


/**
 *
 */
/*package*/ class IntentDispatcherInternal {

    private final Activity activity;
    private final AnimationHelper animationHelper;

    private ActivityAnimation mAnimation;
    private boolean mForResult;
    private int mRequestCode;
    private View mSourceView;

    public IntentDispatcherInternal(final Activity activity) {
        this.animationHelper = new AnimationHelper(activity);
        this.activity = activity;
    }

    private void cleanUp() {
        this.mAnimation = null;
        this.mSourceView = null;
        this.mForResult = false;
        this.mRequestCode = Activity.RESULT_OK;
    }

    public void dispatch(final Intent intent) {
        if (intent == null) {
            AppToast.showDebug(activity, "Intent was null!", AppToast.Length.SHORT);
            NavLog.w("Could not start Activity: Intent was null");
        } else {
            final Bundle theBundle = getBundle(mAnimation);
            try {
                if (mForResult) {
                    ActivityCompat.startActivityForResult(activity, intent, mRequestCode, theBundle);
                } else {
                    ActivityCompat.startActivity(activity, intent, theBundle);
                }
            } catch (final ActivityNotFoundException e) {
                NavLog.w("Error starting activity", e);
            }
        }
        cleanUp();
    }

    public IntentDispatcherInternal forResult(final int requestCode) {
        this.mForResult = true;
        this.mRequestCode = requestCode;
        return this;
    }

    private Bundle getBundle(final ActivityAnimation activityAnimation) {
        if (activityAnimation == null) {
            return new Bundle();
        }

        switch (activityAnimation) {
            case SLIDE_IN_FROM_LEFT:
                return getSlideInFromLeftBundle();
            case SCALE_UP_FROM_VIEW:
                if (mSourceView != null) {
                    return animationHelper.getScaleUpBundle(mSourceView);
                }
            case SLIDE_UP_FROM_BOTTOM:
                return animationHelper.getSlideUpBundle();
            default:
                return new Bundle();
        }
    }

    private Bundle getCustomAnimationBundle(final int animationIn, final int animationOut) {
        final ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.
                makeCustomAnimation(activity, animationIn, animationOut);
        return activityOptionsCompat.toBundle();
    }


    private Bundle getSlideInFromLeftBundle() {
        return getCustomAnimationBundle(R.anim.slide_in_right, R.anim.zoom_out);
    }

    public IntentDispatcherInternal withAnimation(final ActivityAnimation animation) {
        this.mAnimation = animation;
        return this;
    }

    public IntentDispatcherInternal withView(final View sourceView) {
        this.mSourceView = sourceView;
        return this;
    }

}
