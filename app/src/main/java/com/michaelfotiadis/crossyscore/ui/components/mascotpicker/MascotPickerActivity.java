package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.Searchable;
import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public class MascotPickerActivity extends BaseActivity {

    private static final String FRAGMENT_TAG =
            MascotPickerActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, MascotPickerActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);

        setTitle(R.string.title_pick_mascot);

        addContentFragmentIfMissing(
                MascotPickerFragment.newInstance(),
                FRAGMENT_TAG);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchMenu = menu.findItem(R.id.action_search);

        // Initialise the searchview
        @SuppressWarnings("ConstantConditions")
        final SearchView searchView = new SearchView(this.getSupportActionBar().getThemedContext());

        MenuItemCompat.setShowAsAction(searchMenu, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(searchMenu, searchView);
        searchView.setQueryHint(getString(R.string.hint_search_for_a_mascot));
        // Add a OnQueryTextListener


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                submitQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String searchText) {
                submitQuery(searchText);
                return false;
            }
        });

        // Add an expand listener
        MenuItemCompat.setOnActionExpandListener(searchMenu, new MyOnActionExpandListener());
        return super.onCreateOptionsMenu(menu);
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

    private static class MyOnActionExpandListener implements MenuItemCompat.OnActionExpandListener {
        @Override
        public boolean onMenuItemActionExpand(final MenuItem item) {
            return true; // Return true to expand action view
        }

        @Override
        public boolean onMenuItemActionCollapse(final MenuItem item) {
            return true; // Return true to collapse action view
        }
    }
}
