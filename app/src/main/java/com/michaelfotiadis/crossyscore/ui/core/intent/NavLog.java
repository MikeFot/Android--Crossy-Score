package com.michaelfotiadis.crossyscore.ui.core.intent;


import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public final class NavLog {

    private static final String PREFIX = "NAV:";

    private NavLog() {
    }

    public static void d(final String message) {
        AppLog.d(formatMessage(message));
    }

    public static void e(final String message, final Exception e) {
        AppLog.e(formatMessage(message), e);
    }

    public static void e(final String message) {
        AppLog.e(formatMessage(message));
    }

    private static String formatMessage(final String message) {
        return PREFIX + message;
    }

    public static void w(final String message) {
        AppLog.w(formatMessage(message));
    }

    public static void w(final String message, final Exception e) {
        AppLog.w(formatMessage(message), e);
    }
}
