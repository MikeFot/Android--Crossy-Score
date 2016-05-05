package com.michaelfotiadis.crossyscore.ui.core.common.notifications;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.utils.AppLog;


public final class SnackBarNotificationController implements ActivityNotificationController {

    private static final int COORDINATE_LAYOUT_ID = R.id.coordinatorLayout;
    private final View mCoordinateLayout;

    public SnackBarNotificationController(final Activity activity) {
        this(activity.findViewById(COORDINATE_LAYOUT_ID));
    }

    public SnackBarNotificationController(final View view) {
        this.mCoordinateLayout = view;
        if (mCoordinateLayout == null) {
            AppLog.w(this.getClass().getSimpleName() + " instantiated with invalid view");
        }
    }

    @Override
    public void showNotification(final CharSequence message, final CharSequence buttonText, final View.OnClickListener listener) {
        final Snackbar bar = Snackbar.make(mCoordinateLayout, message, Snackbar.LENGTH_LONG);

        if (listener != null) {
            bar.setAction(buttonText, listener);
        }

        bar.show();
    }

    @Override
    public void showNotification(final CharSequence message) {
        this.showNotification(message, null, null);
    }

    @Override
    public void showNotification(final int message, final int actionText, final View.OnClickListener listener) {
        final Snackbar bar = Snackbar.make(mCoordinateLayout, message, Snackbar.LENGTH_LONG);

        if (listener != null) {
            bar.setAction(actionText, listener);
        }

        bar.show();
    }

    @Override
    public void showNotification(final int message) {
        this.showNotification(message, 0, null);
    }
}
