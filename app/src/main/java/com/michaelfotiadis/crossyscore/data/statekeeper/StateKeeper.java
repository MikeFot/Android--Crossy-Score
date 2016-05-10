package com.michaelfotiadis.crossyscore.data.statekeeper;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;

/**
 *
 */
public interface StateKeeper<T extends AppModel> {

    boolean isReady();

    State getState();

    T build();

}
