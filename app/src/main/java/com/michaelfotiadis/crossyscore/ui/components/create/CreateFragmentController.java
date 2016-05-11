package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.core.utils.score.ScoreUtils;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.MascotLoader;
import com.michaelfotiadis.crossyscore.data.loader.PlayerLoader;
import com.michaelfotiadis.crossyscore.data.loader.ScoreLoader;
import com.michaelfotiadis.crossyscore.data.statekeeper.ScoreStateKeeper;
import com.michaelfotiadis.crossyscore.data.validation.ValidationResult;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotViewBinder;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotViewHolder;
import com.michaelfotiadis.crossyscore.ui.components.create.player.ListPlayerAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.notifications.AppToast;
import com.michaelfotiadis.crossyscore.utils.AppConstants;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CreateFragmentController extends BaseController {

    private final ListPlayerAdapter mPlayerAdapter;

    private final CreateFragmentViewHolder mHolder;
    private final CreateFragmentViewBinder mBinder;

    private final ListMascotViewHolder mMascotViewHolder;
    private final ListMascotViewBinder mMascotViewBinder;

    private final ScoreStateKeeper mKeeper;

    private final List<Mascot> mMascots = new ArrayList<>();

    public CreateFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mKeeper = new ScoreStateKeeper();

        mHolder = new CreateFragmentViewHolder(view);
        mBinder = new CreateFragmentViewBinder(activity, createIntentDispatcher());

        mBinder.bind(mHolder);

        mPlayerAdapter = new ListPlayerAdapter(activity);
        mHolder.userSpinner.setAdapter(mPlayerAdapter);

        mHolder.userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                mKeeper.setOwnerId(getPlayer().getId());
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });

        mMascotViewHolder = new ListMascotViewHolder(mHolder.mascotLayout);
        mMascotViewBinder = new ListMascotViewBinder(getActivity(), createIntentDispatcher());

        mHolder.mascotLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Requesting mascot");
                createIntentDispatcher().openMascotPickerActivityForResult(v, AppConstants.REQUEST_CODE_1);
            }
        });


        mHolder.scoreText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                mKeeper.setValue(getScoreValue());
            }

            @Override
            public void afterTextChanged(final Editable s) {

            }
        });

        mHolder.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                saveScore();
            }
        });

        mHolder.addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Adding new player");
                createIntentDispatcher().openAddPlayerActivityForResult(v, AppConstants.REQUEST_CODE_2);
            }
        });

    }

    private void saveScore() {

        final Score score = buildScore();

        if (score != null) {
            AppLog.d("Sending result: " + score.getId());
            final Bundle data = new Bundle();
            data.putParcelable(AppConstants.EXTRA_1, score);
            final Intent intent = new Intent();
            intent.putExtras(data);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }

    private Integer getScoreValue() {

        final String value = mHolder.scoreText.getText().toString();

        if (TextUtils.isEmpty(value)) {
            return -1;
        } else {
            return Integer.valueOf(value);
        }

    }

    private Player getPlayer() {
        return (Player) mHolder.userSpinner.getSelectedItem();
    }


    private Score buildScore() {

        if (getPlayer() != null) {
            mKeeper.setOwnerId(getPlayer().getId());
        }

        final ValidationResult validationResult = mKeeper.validate();

        if (mKeeper.isReady()) {
            return mKeeper.build();
        } else {
            AppLog.w("Invalid score: " + validationResult.getMessage());
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).getNotificationController().showNotification(validationResult.getMessage());
            } else {
                AppToast.show(getActivity(), validationResult.getMessage());
            }
            return null;
        }

    }

    public void setPlayers(final List<Player> items) {
        mPlayerAdapter.setItems(items);
    }

    protected void loadData() {
        loadMascots();
        loadPlayers();
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
                    if (TextUtils.isEmpty(mKeeper.getMascotId())) {
                        setMascot(latestScore.getMascotId());
                    }
                } else {
                    AppLog.w("Latest score is null");
                }
            }
        });
        scoreLoader.loadData();
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
                mMascots.clear();
                mMascots.addAll(mascots);
                if (TextUtils.isEmpty(mKeeper.getMascotId())) {
                    setMascot(mascots.get(0));
                }
                loadScores();
            }
        });

        mascotLoader.loadData();
    }

    private void loadPlayers() {
        final DataFeedLoaderAbstract<Player> playerLoader = new PlayerLoader(getActivity());

        playerLoader.setCallback(new DataFeedLoaderCallback<Player>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load players: " + error);
                showMessage("Failed to load players");
            }

            @Override
            public void onSuccess(final List<Player> result) {
                AppLog.d("Loaded " + result.size() + " users");
                setPlayers(result);
            }
        });
        playerLoader.loadData();
    }

    public void setMascot(final String mascotId) {
        if (!TextUtils.isEmpty(mascotId)) {
            for (final Mascot mascot : mMascots) {
                if (mascotId.equals(mascot.getId())) {
                    setMascot(mascot);
                    break;
                }
            }
        }
        AppLog.w("Could not find mascot for id " + mascotId);
    }

    public void setMascot(final Mascot mascot) {
        if (mascot != null) {
            AppLog.d("Selecting mascot " + mascot.getName());
            mMascotViewBinder.bind(mMascotViewHolder, mascot);
            mKeeper.setMascotId(mascot.getId());
        }
    }

    public void addPlayer(final Player player) {
        if (player != null) {
            mPlayerAdapter.addItem(player);
            mHolder.userSpinner.setSelection(0);
        }
    }
}
