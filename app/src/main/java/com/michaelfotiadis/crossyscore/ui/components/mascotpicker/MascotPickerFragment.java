package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.Searchable;

/**
 *
 */
public class MascotPickerFragment extends BaseFragment implements Searchable {

    private MascotPickerController mController;

    public static BaseFragment newInstance() {
        return new MascotPickerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_default_recycler, container, false);

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

    @Override
    public void setFilter(final String filter) {

    }
}
