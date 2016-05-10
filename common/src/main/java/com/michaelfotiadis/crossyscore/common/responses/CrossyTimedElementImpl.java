package com.michaelfotiadis.crossyscore.common.responses;

import java.util.Calendar;

/**
 *
 */
public class CrossyTimedElementImpl implements CrossyTimedElement {

    final long mCreationTime;

    public CrossyTimedElementImpl() {
        this.mCreationTime = Calendar.getInstance().getTimeInMillis();
    }

    public long getCreationTime() {
        return mCreationTime;
    }
}
