package com.michaelfotiadis.crossyscore.core.data.database;


import com.michaelfotiadis.crossyscore.core.utils.SdkLog;

public final class DbLog {

    private static final String PREFIX = "DB:";

    private DbLog() {
    }

    public static void d(final String message) {
        SdkLog.d(formatMessage(message));
    }

    private static String formatMessage(final String message) {
        return PREFIX + message;
    }

    public static void e(final String message, final Exception e) {
        SdkLog.e(formatMessage(message), e);
    }

    public static void e(final String message) {
        SdkLog.e(formatMessage(message));
    }

    public static void w(final String message) {
        SdkLog.w(formatMessage(message));
    }
}