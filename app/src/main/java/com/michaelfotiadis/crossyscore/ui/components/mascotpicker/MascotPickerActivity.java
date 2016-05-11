package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.Searchable;
import com.michaelfotiadis.crossyscore.ui.core.common.search.EditTextFilterWrapper;
import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public class MascotPickerActivity extends BaseActivity {

    private static final String FRAGMENT_TAG =
            MascotPickerActivity.class.getSimpleName() + "_fragment_tag";
    protected EditText mSearchView;

    public static Intent getInstance(final Context context) {
        return new Intent(context, MascotPickerActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable_fragment_container);

        addContentFragmentIfMissing(
                MascotPickerFragment.newInstance(),
                FRAGMENT_TAG);

        mSearchView = (EditText) getCustomActionBar().findViewById(R.id.search);
        mSearchView.setHint(getString(R.string.hint_search_for_a_mascot));
        new EditTextFilterWrapper(mSearchView, new EditTextFilterWrapper.OnTextChangedListener() {
            @Override
            public void onTextChanged(final String newText) {
                submitQuery(newText);
            }
        });

    }

    private void submitQuery(final String query) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (Searchable.class.isInstance(fragment)) {
            AppLog.w("Submitting query " + query);
            ((Searchable) fragment).setFilter(query);
        } else {
            AppLog.w("Fragment is not searchable");
        }

    }

}
