package com.michaelfotiadis.crossyscore.ui.core.common.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;

/**
 *
 */
/*package*/ class ActionbarNavigationController {

    private final AppCompatActivity mActivity;

    public ActionbarNavigationController(final AppCompatActivity activity) {
        this.mActivity = activity;
    }

    public void bind(final ActionBar actionbar,
                     final Toolbar toolbar) {

        if (showArrow()) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowHomeEnabled(true);

            toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
            toolbar.setNavigationOnClickListener(new NavigationOnClickListener(mActivity));
        }
    }

    private boolean showArrow() {
        return mActivity.getParentActivityIntent() != null;
    }

    private static class NavigationOnClickListener implements View.OnClickListener {

        private final AppCompatActivity activity;

        public NavigationOnClickListener(final AppCompatActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(final View v) {
            // NavUtils.navigateUpFromSameTask(activity);
            activity.onBackPressed();
        }
    }
}
