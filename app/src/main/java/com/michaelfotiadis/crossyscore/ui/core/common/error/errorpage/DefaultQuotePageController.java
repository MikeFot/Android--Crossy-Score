package com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class DefaultQuotePageController implements QuotePageController {

    @Bind(R.id.error_button)
    protected Button mActionButton;
    @Bind(R.id.error_quote_author)
    protected TextView mAuthor;
    @Bind(R.id.error_description)
    protected TextView mErrorDescription;
    @Bind(R.id.error_quote)
    protected TextView mQuote;

    public DefaultQuotePageController(final View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void display(final CharSequence message) {
        display(message, null);
    }

    @Override
    public void display(final CharSequence message, final QuoteOnClickListenerWrapper listenerWrapper) {

        final Quote quote = mQuotePicker.getQuote();
        mQuote.setText(quote.getQuote());
        mAuthor.setText(quote.getAuthor());

        mErrorDescription.setText(message);

        if (listenerWrapper == null || listenerWrapper.getListener() == null) {
            mActionButton.setVisibility(View.GONE);
        } else {
            mActionButton.setVisibility(View.VISIBLE);
            // set up the action button label
            if (listenerWrapper.getResId() == 0) {
                mActionButton.setText("Try again");
            } else {
                mActionButton.setText(listenerWrapper.getResId());
            }
            mActionButton.setOnClickListener(listenerWrapper.getListener());
        }

    }

}
