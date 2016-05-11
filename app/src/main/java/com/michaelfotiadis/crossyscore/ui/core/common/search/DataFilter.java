package com.michaelfotiadis.crossyscore.ui.core.common.search;

/**
 *
 */
public interface DataFilter<D> {

    void filter(final String query, final FilterFinishedCallback<D> callback);

    void setEmptyQueryBehaviour(final EmptyQueryBehaviour behaviour);

    enum EmptyQueryBehaviour {
        SHOW_ALL,
        SHOW_NONE
    }
}
