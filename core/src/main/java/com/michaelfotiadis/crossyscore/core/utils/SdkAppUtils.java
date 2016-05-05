package com.michaelfotiadis.crossyscore.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.concurrent.TimeUnit;

/**
 */
public final class SdkAppUtils {

    // Sets the amount of time an idle thread waits before terminating
    public static final int KEEP_ALIVE_TIME = 1;
    // Sets the Time Unit to seconds
    public static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private SdkAppUtils() {
        // DO NOT INSTANTIATE
    }

    public static String getApplicationVersion(final Context context) {
        try {
            final PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (pInfo.versionName != null) {
                return pInfo.versionName;
            } else {
                return "0.0";
            }
        } catch (final PackageManager.NameNotFoundException e) {
            SdkLog.e("Error getting package name", e);
            return "0.1";
        }

    }

    public static int getNumberOfAvailableThreads() {

        return Runtime.getRuntime().availableProcessors();
    }

}
