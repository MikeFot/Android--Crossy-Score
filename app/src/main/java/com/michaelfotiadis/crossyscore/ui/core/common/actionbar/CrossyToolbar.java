package com.michaelfotiadis.crossyscore.ui.core.common.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.R;


/**
 *
 */
public class CrossyToolbar implements CrossyActionBar {

    private static final int CUSTOM_TITLE_ID = R.id.toolbar_title_view;
    private final ActionBar mActionbar;
    private final AppCompatActivity mActivity;
    private final TextView mTitleTextView;
    private final Toolbar mToolbar;

    /**
     * Initialises a new Actionbar wrapper.
     * This wrapper needs to be instantiatied after {@link AppCompatActivity#setSupportActionBar(Toolbar)} has been called.
     *
     * @param activity The {@link AppCompatActivity} which contains the {@param toolbar}
     * @param toolbar  The {@link Toolbar} view.
     */
    public CrossyToolbar(final AppCompatActivity activity, final Toolbar toolbar) {
        mActivity = activity;
        this.mToolbar = toolbar;
        this.mActionbar = activity.getSupportActionBar();
        this.mTitleTextView = (TextView) toolbar.findViewById(CUSTOM_TITLE_ID);

        if (mActionbar == null) {
            throw new IllegalStateException("The actionbar is null. Did you forget to call AppCompatActivity.setSupportActionBar(Toolbar)?");
        }

        if (mTitleTextView == null) {
            throw new IllegalStateException("Passed toolbar is not of supported type");
        }

        final ActionbarNavigationController navController = new ActionbarNavigationController(mActivity);
        navController.bind(mActionbar, mToolbar);
    }

    @Override
    public View findViewById(final int id) {
        return mToolbar.findViewById(id);
    }

    @Override
    public void goTransparent() {
        mToolbar.setBackgroundColor(mActivity.getResources().getColor(android.R.color.transparent));
    }

    @Override
    public void hide() {
        mActionbar.hide();
    }

    @Override
    public void setTitle(final CharSequence title, final int[] colors) {
        setTitle(title);
    }

    @Override
    public void setTitle(final CharSequence title) {
        mActionbar.setDisplayShowTitleEnabled(false);
        mTitleTextView.setText(title);
    }

    @Override
    public void setTitleAlpha(final float alpha) {
        mTitleTextView.setAlpha(alpha);
    }

    @Override
    public void show() {
        mActionbar.show();
    }
}
