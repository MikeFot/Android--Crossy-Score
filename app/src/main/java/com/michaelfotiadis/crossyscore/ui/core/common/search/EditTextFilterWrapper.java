package com.michaelfotiadis.crossyscore.ui.core.common.search;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 *
 */
public class EditTextFilterWrapper {

    public EditTextFilterWrapper(final EditText editText, final OnTextChangedListener listener) {
        editText.addTextChangedListener(new MyTextWatcher(listener));
    }


    public interface OnTextChangedListener {

        void onTextChanged(final String newText);
    }

    private static class MyTextWatcher implements TextWatcher {

        private final OnTextChangedListener listener;

        public MyTextWatcher(final OnTextChangedListener listener) {
            this.listener = listener;
        }

        @Override
        public void afterTextChanged(final Editable s) {
            listener.onTextChanged(s.toString());
        }

        @Override
        public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
        }

        @Override
        public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        }
    }
}
