package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.ui.core.common.fragment.BaseFragment;
import com.michaelfotiadis.crossyscore.utils.AppConstants;
import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public class MainFragment extends BaseFragment {

    private MainFragmentController mController;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_main, container, false);

        mController = new MainFragmentController(getActivity(), view);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mController.loadData();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            case AppConstants.REQUEST_CODE_3:
                if (resultCode == Activity.RESULT_OK) {
                    final Score score = data.getExtras().getParcelable(AppConstants.EXTRA_1);
                    if (score != null) {
                        AppLog.d("result: " + score.getId());
                        mController.saveScore(score);
                    } else {
                        AppLog.e("Null mascot result");
                    }
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
