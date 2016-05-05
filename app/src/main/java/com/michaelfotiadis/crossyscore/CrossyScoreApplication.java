package com.michaelfotiadis.crossyscore;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import io.fabric.sdk.android.Fabric;

/**
 *
 */
public class CrossyScoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        AppLog.d("Dev Mode is " + BuildConfig.DEV_MODE);
        CrossyCore.init(this, BuildConfig.DEV_MODE);
    }

}
