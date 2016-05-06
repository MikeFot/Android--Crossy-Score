package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;
import android.os.Parcelable;

import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;

import java.util.List;

/**
 *
 */
public abstract class DataFeedLoaderAbstract<D extends Parcelable> {

    private final Activity mActivity;
    private DataFeedLoaderCallback<D> mCallback;

    public DataFeedLoaderAbstract(final Activity activity) {
        mActivity = activity;
    }

    protected Activity getActivity() {
        return mActivity;
    }

    public abstract void loadData();

    protected void notifyError(final UiDataLoadError error) {
        if (mCallback != null) {
            if (mActivity == null) {
                mCallback.onError(error);
            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onError(error);
                    }
                });
            }
        }
    }

    protected void notifySuccess(final List<D> items) {
        if (mCallback != null) {
            if (mActivity == null) {
                mCallback.onSuccess(items);
            } else {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onSuccess(items);
                    }
                });
            }
        }
    }

    public void setCallback(final DataFeedLoaderCallback<D> callback) {
        mCallback = callback;
    }

}