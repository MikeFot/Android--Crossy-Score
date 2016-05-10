package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.models.score.ScoreImpl;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.core.utils.score.ScoreUtils;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.MascotLoader;
import com.michaelfotiadis.crossyscore.data.loader.ScoreLoader;
import com.michaelfotiadis.crossyscore.data.loader.UserLoader;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotViewBinder;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotViewHolder;
import com.michaelfotiadis.crossyscore.ui.components.create.player.ListUserAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.AppToast;
import com.michaelfotiadis.crossyscore.utils.AppConstants;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class CreateFragmentController extends BaseController {

    private final ListUserAdapter mUserAdapter;

    private final CreateFragmentViewHolder mHolder;
    private final CreateFragmentViewBinder mBinder;

    private final ListMascotViewHolder mMascotViewHolder;
    private final ListMascotViewBinder mMascotViewBinder;

    private Mascot mSelectedMascot;

    public CreateFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new CreateFragmentViewHolder(view);
        mBinder = new CreateFragmentViewBinder(activity, createIntentDispatcher());

        mBinder.bind(mHolder);

        mUserAdapter = new ListUserAdapter(activity);
        mHolder.userSpinner.setAdapter(mUserAdapter);


        mMascotViewHolder = new ListMascotViewHolder(mHolder.mascotLayout);
        mMascotViewBinder = new ListMascotViewBinder(getActivity(), createIntentDispatcher());

        mHolder.mascotLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Requesting mascot");
                createIntentDispatcher().openMascotPickerActivityForResult(v, AppConstants.REQUEST_CODE_1);
            }
        });

        mHolder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Is score valid: " + validate());
                saveScore();

            }
        });

    }

    private void saveScore() {

        final Score score = buildScore();
        if (score != null) {
            AppLog.d("Created score " + score);
            CrossyCore.getDataProvider().getScores().upsert(score);
            getActivity().finish();
        } else {
            AppToast.show(getActivity(), "Something is missing");
        }

    }

    private Score buildScore() {
        if (validate()) {

            final User user = (User) mHolder.userSpinner.getSelectedItem();

            return ScoreImpl.newBuilder()
                    .withMascot(mSelectedMascot)
                    .withOwnerId(user.getId())
                    .withTimeStamp(System.currentTimeMillis())
                    .withValue(Integer.valueOf(mHolder.scoreText.getText().toString()))
                    .build();

        } else {
            return null;
        }
    }

    public void setUsers(final List<User> items) {
        mUserAdapter.setItems(items);
    }

    protected void loadData() {
        loadMascots();
        loadUsers();

    }

    private void loadScores() {

        final DataFeedLoaderAbstract<Score> scoreLoader = new ScoreLoader(getActivity());

        scoreLoader.setCallback(new DataFeedLoaderCallback<Score>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load scores: " + error);
            }

            @Override
            public void onSuccess(final List<Score> result) {
                final Score latestScore = ScoreUtils.getLatestScore(result);

                if (latestScore != null) {
                    AppLog.d("Latest score is " + latestScore.toString());
                    setMascot(latestScore.getMascot());
                } else {
                    AppLog.w("Latest score is null");
                }
            }
        });
        scoreLoader.loadData();
    }

    private boolean validate() {

        if (mSelectedMascot == null) {
            return false;
        } else if (mHolder.userSpinner.getSelectedItem() == null) {
            return false;
        } else if (TextUtils.isEmpty(mHolder.scoreText.toString())) {
            return false;
        } else {
            return true;
        }

    }

    private void loadMascots() {
        final DataFeedLoaderAbstract<Mascot> mascotLoader = new MascotLoader(getActivity());
        mascotLoader.setCallback(new DataFeedLoaderCallback<Mascot>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load mascots: " + error);
            }

            @Override
            public void onSuccess(final List<Mascot> mascots) {
                loadScores();
            }
        });

        mascotLoader.loadData();
    }

    private void loadUsers() {
        final DataFeedLoaderAbstract<User> userLoader = new UserLoader(getActivity());

        userLoader.setCallback(new DataFeedLoaderCallback<User>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load users: " + error);
                showMessage("Failed to load Users");
            }

            @Override
            public void onSuccess(final List<User> result) {
                AppLog.d("Loaded " + result.size() + " users");
                setUsers(result);
            }
        });

        userLoader.loadData();
    }

    public void setMascot(final Mascot mascot) {
        if (mascot != null) {
            AppLog.d("Selecting mascot " + mascot.getName());
            mMascotViewBinder.bind(mMascotViewHolder, mascot);
            mSelectedMascot = mascot;
        }
    }
}
