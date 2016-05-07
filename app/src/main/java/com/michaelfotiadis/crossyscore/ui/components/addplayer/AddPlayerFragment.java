package com.michaelfotiadis.crossyscore.ui.components.addplayer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.AvatarLoader;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

public class AddPlayerFragment extends BaseFragment {

    private AddPlayerFragmentController mController;

    public AddPlayerFragment() {
        // Required empty public constructor
    }

    public static AddPlayerFragment newInstance() {
        return new AddPlayerFragment();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_add_player, container, false);

        mController = new AddPlayerFragmentController(getActivity(), view);

        mController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Player player = mController.createPlayer();
                if (player != null) {

                    CrossyCore.getDataProvider().getPlayers().upsert(player);
                    getActivity().finish();

                }

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {

        final DataFeedLoaderAbstract<ImageContainer> imageLoader = new AvatarLoader(getActivity());

        imageLoader.setCallback(new DataFeedLoaderCallback<ImageContainer>() {
            @Override
            public void onError(final UiDataLoadError error) {
                getNotificationController().showNotification("Failed to load Avatars");
            }

            @Override
            public void onSuccess(final List<ImageContainer> images) {
                AppLog.d("Loaded " + images.size() + " users");
                mController.setImages(images);
            }
        });

        imageLoader.loadData();

    }
}
