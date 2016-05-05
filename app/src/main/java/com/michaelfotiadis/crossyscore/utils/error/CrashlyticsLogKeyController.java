package com.michaelfotiadis.crossyscore.utils.error;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;

import com.crashlytics.android.Crashlytics;
import com.michaelfotiadis.crossyscore.utils.date.UtcDateFormatter;

import java.util.Date;
import java.util.Locale;

public final class CrashlyticsLogKeyController {

    private static final String APPLICATION_CREATED = "APPLICATION_CREATED";
    private static final String APPLICATION_FIRST_INSTALLED = "APPLICATION_FIRST_INSTALLED";
    private static final String APPLICATION_LAST_UPDATED = "APPLICATION_LAST_UPDATED";
    private static final String LAST_ACTIVITY_CREATED = "LAST_ACTIVITY_CREATED";
    private static final String LAST_ACTIVITY_PAUSED = "LAST_ACTIVITY_PAUSED";
    private static final String LAST_ACTIVITY_RESUMED = "LAST_ACTIVITY_RESUMED";
    private static final String LAST_FRAGMENT_CREATED = "LAST_FRAGMENT_CREATED";
    private static final String LAST_FRAGMENT_PAUSED = "LAST_FRAGMENT_PAUSED";
    private static final String LAST_FRAGMENT_RESUMED = "LAST_FRAGMENT_RESUMED";
    private static final String PHONE_LOCALE = "PHONE_LOCALE";
    private static final String TIME_FORMAT_JSON = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final UtcDateFormatter TIME_FORMATTER = new UtcDateFormatter(TIME_FORMAT_JSON, Locale.US);

    private CrashlyticsLogKeyController() {
        // No instantiation
    }

    private static String getClassName(final Object object) {
        return ((Object) object).getClass().getSimpleName();
    }

    public static void init(final Context context) {
        String firstInstallTime;
        String lastUpdateTime;

        try {
            final PackageManager manager = context.getPackageManager();
            final PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            firstInstallTime = TIME_FORMATTER.format(new Date(info.firstInstallTime));
            lastUpdateTime = TIME_FORMATTER.format(new Date(info.lastUpdateTime));
        } catch (final PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            firstInstallTime = "n/a";
            lastUpdateTime = "n/a";
        }

        Crashlytics.setString(PHONE_LOCALE, toString(Locale.getDefault()));
        Crashlytics.setString(APPLICATION_FIRST_INSTALLED, firstInstallTime);
        Crashlytics.setString(APPLICATION_LAST_UPDATED, lastUpdateTime);
    }

    public static void onCreate(final Application application) {
        Crashlytics.setString(APPLICATION_CREATED, timestamp(getClassName(application)));
    }

    public static void onCreate(final Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_CREATED, timestamp(getClassName(fragment)));
    }

    public static void onCreate(final android.app.Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_CREATED, timestamp(getClassName(fragment)));
    }

    public static void onCreate(final Activity activity) {
        Crashlytics.setString(LAST_ACTIVITY_CREATED, timestamp(getClassName(activity)));
    }

    public static void onPause(final Activity activity) {
        Crashlytics.setString(LAST_ACTIVITY_PAUSED, timestamp(getClassName(activity)));
    }

    public static void onPause(final Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_PAUSED, timestamp(getClassName(fragment)));
    }

    public static void onPause(final android.app.Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_PAUSED, timestamp(getClassName(fragment)));
    }

    public static void onResume(final Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_RESUMED, timestamp(getClassName(fragment)));
    }

    public static void onResume(final Activity activity) {
        Crashlytics.setString(LAST_ACTIVITY_RESUMED, timestamp(getClassName(activity)));
    }

    public static void onResume(final android.app.Fragment fragment) {
        Crashlytics.setString(LAST_FRAGMENT_RESUMED, timestamp(getClassName(fragment)));
    }

    private static String timestamp(final String message) {
        return message + " -- at " + TIME_FORMATTER.format(new Date());
    }

    private static String toString(final Object object) {
        if (object == null) {
            return "n/a";
        } else {
            return object.toString();
        }
    }
}
