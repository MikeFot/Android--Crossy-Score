package com.michaelfotiadis.crossyscore.ui.components.addplayer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;

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
        mController.loadImages();
    }
}
