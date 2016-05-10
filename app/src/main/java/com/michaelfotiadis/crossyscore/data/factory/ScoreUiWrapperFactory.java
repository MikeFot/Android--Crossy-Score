package com.michaelfotiadis.crossyscore.data.factory;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.responses.CrossyCallback;
import com.michaelfotiadis.crossyscore.common.responses.CrossyDeliverable;
import com.michaelfotiadis.crossyscore.common.responses.CrossyError;
import com.michaelfotiadis.crossyscore.common.responses.CrossyErrorKind;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.MascotLoader;
import com.michaelfotiadis.crossyscore.data.loader.PlayerLoader;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapperImpl;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ScoreUiWrapperFactory {

    private final Activity mActivity;

    private List<Score> mScores;
    private List<Mascot> mMascots;
    private List<Player> mPlayers;
    private CrossyCallback<List<ScoreUiWrapper>> mCallback;


    public ScoreUiWrapperFactory(final Activity activity) {
        this.mActivity = activity;
    }

    public void createScoreUiWrappers(final List<Score> scores,
                                      final List<Player> players,
                                      final List<Mascot> mascots,
                                      final CrossyCallback<List<ScoreUiWrapper>> callback) {

        mScores = scores;
        mPlayers = players;
        mMascots = mascots;
        mCallback = callback;

        buildScoreWrappers();
    }

    public void createScoreUiWrappers(final List<Score> scores,
                                      final CrossyCallback<List<ScoreUiWrapper>> callback) {

        mScores = scores;
        mCallback = callback;

        loadMascots();
    }

    private void loadMascots() {
        final DataFeedLoaderAbstract<Mascot> mascotLoader = new MascotLoader(mActivity);
        mascotLoader.setCallback(new DataFeedLoaderCallback<Mascot>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load mascots: " + error);
                mCallback.onFailure(CrossyError.from("Failed to load mascots", CrossyErrorKind.NO_CONTENT_RETURNED));
            }

            @Override
            public void onSuccess(final List<Mascot> mascots) {
                AppLog.d("Loaded " + mascots.size() + " mascots");
                mMascots = mascots;
                loadPlayers();
            }
        });

        mascotLoader.loadData();
    }

    private void loadPlayers() {
        final DataFeedLoaderAbstract<Player> playerLoader = new PlayerLoader(mActivity);

        playerLoader.setCallback(new DataFeedLoaderCallback<Player>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load players: " + error);
                mCallback.onFailure(CrossyError.from("Failed to load players", CrossyErrorKind.NO_CONTENT_RETURNED));
            }

            @Override
            public void onSuccess(final List<Player> players) {
                AppLog.d("Loaded " + players.size() + " players");
                mPlayers = players;
                buildScoreWrappers();
            }
        });

        playerLoader.loadData();
    }

    private void buildScoreWrappers() {

        final List<ScoreUiWrapper> wrappers = new ArrayList<>();

        for (final Score score : mScores) {

            final ScoreUiWrapperImpl.Builder builder = ScoreUiWrapperImpl.newBuilder();

            builder.withValue(score.getValue());
            builder.withTimeStamp(score.getTimeStamp());

            for (final Mascot mascot : mMascots) {
                if (score.getMascotId().equals(mascot.getId())) {
                    builder.withMascotName(mascot.getName());
                    builder.withMascotResId(mascot.getResId());
                    break;
                }
            }

            for (final Player player : mPlayers) {
                if (score.getOwnerId().equals(player.getId())) {
                    builder.withPlayerName(player.getName());
                    builder.withPlayerResId(player.getDrawableResId());
                }
            }
            wrappers.add(builder.build());
        }

        mCallback.onSuccess(CrossyDeliverable.from(wrappers));

    }

}
