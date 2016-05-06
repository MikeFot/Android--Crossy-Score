package com.michaelfotiadis.crossyscore.ui.components.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.UserLoader;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class CreateFragment extends BaseFragment {

    private CreateController mController;

    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_create, container, false);

        mController = new CreateController(getActivity(), view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List<Mascot> mascots = CrossyCore.getDataProvider().getMascots();

        mController.setMascots(mascots);

        final DataFeedLoaderAbstract<User> loader = new UserLoader(getActivity());

        loader.setCallback(new DataFeedLoaderCallback<User>() {
            @Override
            public void onError(final UiDataLoadError error) {
                getNotificationController().showNotification("Failed to load Users");
            }

            @Override
            public void onSuccess(final List<User> result) {
                AppLog.d("Loaded " + result.size() + " users");
                mController.setUsers(result);
            }
        });

        loader.loadData();

    }

}
