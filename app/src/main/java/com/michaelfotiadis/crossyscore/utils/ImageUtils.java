package com.michaelfotiadis.crossyscore.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.michaelfotiadis.crossyscore.R;

import java.util.regex.Pattern;

import co.uk.alt236.reflectivedrawableloader.ReflectiveDrawableLoader;

/**
 *
 */
public class ImageUtils {

    public static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;
    private static final Pattern PATTERN_SPACE = Pattern.compile(" ");
    private static final Pattern PATTERN_DASH = Pattern.compile("-");
    private static final Pattern PATTERN_SPECIAL = Pattern.compile("[^()|\\- a-zA-Z0-9]");

    private final Context mContext;
    private final ReflectiveDrawableLoader mRefLoaderInstance;

    public ImageUtils(final Context context) {
        mContext = context;
        mRefLoaderInstance = ReflectiveDrawableLoader.getInstance(mContext);
        mRefLoaderInstance.setLogErrors(true);
    }

    public enum IMAGE_TYPE {
        NORMAL, LIST
    }

    private static String sanitiseName(final String name) {
        String noSpecialCharacters = PATTERN_SPECIAL.matcher(name).replaceAll("_");
        noSpecialCharacters = PATTERN_DASH.matcher(noSpecialCharacters).replaceAll("_");
        return PATTERN_SPACE.matcher(noSpecialCharacters).replaceAll("_").toLowerCase();
    }

    public int getImageIdReflectively(final String drawableName, final IMAGE_TYPE type) {

        AppLog.d("Looking for drawable " + drawableName);
        if (type == IMAGE_TYPE.NORMAL) {
            return mRefLoaderInstance.
                    getDrawableId(drawableName, "", DEFAULT_IMAGE_PLACEHOLDER);
        } else if (type == IMAGE_TYPE.LIST) {
            return mRefLoaderInstance.
                    getListDrawableId(drawableName, "", DEFAULT_IMAGE_PLACEHOLDER);
        } else {
            return R.drawable.ic_default;
        }

    }

    public void loadImageToViewReflectively(final ImageView view, final String drawableName, final IMAGE_TYPE type) {
        if (drawableName == null) {
            view.setImageDrawable(ContextCompat.getDrawable(mContext, DEFAULT_IMAGE_PLACEHOLDER));
        } else {
            view.setImageDrawable(ContextCompat.getDrawable(
                    mContext,
                    getImageIdReflectively(sanitiseName(drawableName), type))
            );
        }
    }

}
