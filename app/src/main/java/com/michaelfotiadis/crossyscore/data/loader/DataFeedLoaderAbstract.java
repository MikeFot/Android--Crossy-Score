package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;
import android.os.Parcelable;

/**
 *
 */
public abstract class DataFeedLoaderAbstract<D extends Parcelable> {

    private final Activity mActivity;

    public DataFeedLoaderAbstract(final Activity activity) {
        mActivity = activity;
    }

    protected Activity getActivity() {
        return mActivity;
    }

    public abstract D loadData();

}