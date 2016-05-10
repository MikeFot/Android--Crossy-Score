package com.michaelfotiadis.crossyscore.common.responses;

/**
 * Wrapper object for loading results.
 * Contains the object and the datasource type
 */
public final class CrossyDeliverable<T> implements CrossyTimedElement {

    private final T mObjectContent;
    private final CrossyTimedElement mTimedElement;

    public CrossyDeliverable(final T parcelableObject) {
        this.mObjectContent = parcelableObject;
        this.mTimedElement = new CrossyTimedElementImpl();
    }

    public T getContent() {
        return mObjectContent;
    }

    @Override
    public long getCreationTime() {
        return mTimedElement.getCreationTime();
    }


    public static <T> CrossyDeliverable<T> from(final T parcelableObject) {
        return new CrossyDeliverable<>(parcelableObject);
    }


}
