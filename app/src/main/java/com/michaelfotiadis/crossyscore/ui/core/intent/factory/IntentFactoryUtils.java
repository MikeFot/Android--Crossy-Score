package com.michaelfotiadis.crossyscore.ui.core.intent.factory;

import android.net.Uri;
import android.text.TextUtils;

/**
 *
 */
public final class IntentFactoryUtils {

    private IntentFactoryUtils() {
        // No instantiation.
    }

    public static Uri parseUri(final String uriString) {
        if (TextUtils.isEmpty(uriString)) {
            return null;
        } else {
            return Uri.parse(uriString);
        }
    }
}
