package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;
import com.michaelfotiadis.crossyscore.utils.AppConstants;
import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public class CreateFragment extends BaseFragment {

    private CreateFragmentController mController;

    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_create, container, false);

        mController = new CreateFragmentController(getActivity(), view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        mController.loadData();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case AppConstants.REQUEST_CODE_1:
                if (resultCode == Activity.RESULT_OK) {
                    final Mascot mascot = data.getExtras().getParcelable(AppConstants.EXTRA_1);
                    if (mascot != null) {
                        AppLog.d("result:" + mascot.getId());
                        mController.setMascot(mascot);
                    } else {
                        AppLog.e("Null mascot result");
                    }


                }
                break;
        }

    }
}
