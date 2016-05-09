package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;

/**
 *
 */
public class MascotPickerFragment extends BaseFragment {

    private MascotPickerController mController;

    public static BaseFragment newInstance() {
        return new MascotPickerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_searchable_recycler, container, false);

        mController = new MascotPickerController(getActivity(), view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        mController.loadMascots();
    }

}
