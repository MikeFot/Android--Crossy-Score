package com.michaelfotiadis.crossyscore.common.responses;

/**
 * Callback interface
 *
 * @param <T> object
 */
public interface CrossyCallback<T> {

    void onFailure(final CrossyError error);

    void onSuccess(final CrossyDeliverable<T> deliverable);
}