package com.michaelfotiadis.crossyscore.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 *
 */
public final class NetworkUtils {

    private NetworkUtils() {
    }

    public static boolean isConnected(final Context context) {
        if (context == null) {
            return false;
        } else {
            final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
        }
    }

}
