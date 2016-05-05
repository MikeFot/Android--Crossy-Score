package com.michaelfotiadis.crossyscore.ui.core.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.actionbar.ActionBarred;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.ActivityNotificationController;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.SnackBarNotificationController;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcherImpl;
import com.michaelfotiadis.crossyscore.utils.AppLog;
import com.michaelfotiadis.crossyscore.utils.error.CrashlyticsLogKeyController;


/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements ActionBarred {

    private Toolbar mCustomActionbar;
    private ActivityNotificationController mNotificationController;
    private IntentDispatcher mIntentDispatcher;

    protected void addContentFragmentIfMissing(final Fragment fragment, final String fragmentTag) {
        if (getSupportFragmentManager().findFragmentByTag(fragmentTag) == null) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag);
            fragmentTransaction.commit();
        }
    }

    public Toolbar getToolBar() {
        return mCustomActionbar;
    }

    protected Bundle getExtras() {
        return getIntent().getExtras();
    }


    public ActivityNotificationController getNotificationController() {
        return mNotificationController;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (final Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    @Override
    public void onBackPressed() {
        if (this instanceof BackBlocker) {
            final boolean backBlocked = ((BackBlocker) this).isBackBlockingEnabled();
            if (!backBlocked) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCommon(savedInstanceState);
    }

    private void onCreateCommon(final Bundle savedInstanceState) {
        CrashlyticsLogKeyController.onCreate(this);
        mIntentDispatcher = new IntentDispatcherImpl(this);
    }

    protected final void onCreateFullScreen(final Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        onCreateCommon(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CrashlyticsLogKeyController.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CrashlyticsLogKeyController.onResume(this);
    }


    @Override
    public void setContentView(final int layoutResID) {
        super.setContentView(layoutResID);
        setupActionbar();
        mNotificationController = new SnackBarNotificationController(this);
    }

    @Override
    public void setContentView(final View view) {
        throw new UnsupportedOperationException("Only setContentView(layoutResID) is supported");
    }

    @Override
    public void setTitle(final CharSequence title) {
        mCustomActionbar.setTitle(title);
    }

    protected void setupActionbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            mCustomActionbar = toolbar;
            setTitle(getString(R.string.app_name));
        } else {
            AppLog.w(this.getClass().getName() + ": Null toolbar");
        }
    }

}
