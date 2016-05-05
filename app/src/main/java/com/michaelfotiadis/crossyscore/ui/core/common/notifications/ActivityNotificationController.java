package com.michaelfotiadis.crossyscore.ui.core.common.notifications;

import android.view.View;

public interface ActivityNotificationController {

    void showNotification(final CharSequence message, final CharSequence actionText, final View.OnClickListener listener);

    void showNotification(final CharSequence message);

    void showNotification(final int message, final int actionText, final View.OnClickListener listener);

    void showNotification(final int message);

}
