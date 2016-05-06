package com.michaelfotiadis.crossyscore.ui.core.common.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.ActivityNotificationController;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcherImpl;
import com.michaelfotiadis.crossyscore.utils.error.CrashlyticsLogKeyController;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private IntentDispatcher mIntentDispatcher;


    protected IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    protected ActivityNotificationController getNotificationController() {
        return ((BaseActivity) getActivity()).getNotificationController();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mIntentDispatcher = new IntentDispatcherImpl(activity);
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        CrashlyticsLogKeyController.onCreate(this);
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        CrashlyticsLogKeyController.onPause(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        CrashlyticsLogKeyController.onResume(this);
        super.onResume();
    }
}