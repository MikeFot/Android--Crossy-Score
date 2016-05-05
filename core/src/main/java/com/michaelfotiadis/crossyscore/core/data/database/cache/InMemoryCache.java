package com.michaelfotiadis.crossyscore.core.data.database.cache;

/**
 *
 */

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryCache<D extends AppModel> {

    private final Object mLock = new Object();
    private List<D> mList = new ArrayList<>();

    public synchronized void clear() {
        //DbLog.d("Cache: Clearing");
        set(null);
    }

    public synchronized void set(final List<D> list) {
        synchronized (mLock) {
            //SdkLog.d("Cache: Setting");
            if (list == null) {
                mList = Collections.unmodifiableList(Collections.<D>emptyList());
            } else {
                mList = Collections.unmodifiableList(list);
            }

        }
    }

    public synchronized List<D> get() {
        synchronized (mLock) {
            //SdkLog.d("Cache: Getting");
            return mList;
        }
    }

    public synchronized boolean isPopulated() {
        synchronized (mLock) {
            return !mList.isEmpty();
        }
    }
}
