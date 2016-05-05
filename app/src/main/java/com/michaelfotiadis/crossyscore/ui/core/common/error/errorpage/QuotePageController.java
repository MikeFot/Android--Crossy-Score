package com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage;

/**
 *
 */
public interface QuotePageController {

    void display(final CharSequence message);

    void display(CharSequence message, QuoteOnClickListenerWrapper listenerWrapper);
}
