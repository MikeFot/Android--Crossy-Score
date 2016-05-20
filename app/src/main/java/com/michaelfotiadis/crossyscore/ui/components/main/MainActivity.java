package com.michaelfotiadis.crossyscore.ui.components.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.components.score.ScoreFragment;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.Searchable;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.Sortable;
import com.michaelfotiadis.crossyscore.utils.AppLog;

public class MainActivity extends BaseActivity {

    private static final String FRAGMENT_TAG =
            MainActivity.class.getSimpleName() + "_fragment_tag";

    public static Intent getInstance(final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_fragment_container);

        addContentFragmentIfMissing(ScoreFragment.newInstance(), FRAGMENT_TAG);

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        setupSearchMenu(menu.findItem(R.id.action_search));

        setupSortMenu(menu.findItem(R.id.action_sort));

        return super.onCreateOptionsMenu(menu);
    }

    private void setupSortMenu(final MenuItem sortMenu) {

        sortMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem menuItem) {
                sortData();
                return true;
            }
        });

    }

    private void setupSearchMenu(final MenuItem searchMenu) {
        // Initialise the searchview
        @SuppressWarnings("ConstantConditions")
        final SearchView searchView = new SearchView(this.getSupportActionBar().getThemedContext());

        MenuItemCompat.setShowAsAction(searchMenu, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(searchMenu, searchView);
        searchView.setQueryHint(getString(R.string.hint_search));
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
    }

    private void sortData() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (Sortable.class.isInstance(fragment)) {
            ((Sortable) fragment).sort();
        } else {
            AppLog.w("Fragment is not sortable");
        }

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
