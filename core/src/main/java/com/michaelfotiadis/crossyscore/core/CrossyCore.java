package com.michaelfotiadis.crossyscore.core;

import android.content.Context;

import com.michaelfotiadis.crossyscore.core.data.database.CoreDatabase;
import com.michaelfotiadis.crossyscore.core.utils.SdkLog;

/**
 */
public final class CrossyCore {

    private static final Object LOCK = new Object();
    private static CrossyCore sInstance;
    private boolean mIsDebugEnabled;

    private CrossyCore() {
        // DO NOT INSTANTIATE
    }

    /* package */
    static CrossyCore getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = new CrossyCore();
                }
            }
        }
        return sInstance;
    }

    public static synchronized void init(final Context applicationContext,
                                         final boolean isDebug) {

        if (applicationContext == null) {
            throw new NullPointerException("Null application context");
        }

        final Context context = applicationContext.getApplicationContext();
        getInstance().mIsDebugEnabled = isDebug;
        CoreDatabase.setContext(context);

        SdkLog.d("Core Initialised");
    }

    public static boolean isDebugEnabled() {
        return getInstance().mIsDebugEnabled;
    }

    public static CoreDatabase getDatabase() {
        return CoreDatabase.getInstance();
    }

}
