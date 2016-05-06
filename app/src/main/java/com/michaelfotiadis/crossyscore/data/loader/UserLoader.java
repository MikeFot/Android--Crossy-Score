package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.helper.UserFactory;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class UserLoader extends DataFeedLoaderAbstract<User> {

    public UserLoader(final Activity activity) {
        super(activity);
    }

    @Override
    public void loadData() {

        final List<Score> scores = CrossyCore.getDataProvider().getScores().getAll();

        if (scores == null) {
            notifyError(new UiDataLoadError("Null scores found", UiDataLoadError.ErrorKind.NO_DATA, false));
            return;
        } else {
            AppLog.d("Loaded " + scores.size() + " scores");
        }


        final List<Player> players = CrossyCore.getDataProvider().getPlayers().getAll();
        if (players == null) {
            notifyError(new UiDataLoadError("Null players found", UiDataLoadError.ErrorKind.NO_DATA, false));
            return;
        } else {
            AppLog.d("Loaded " + players.size() + " players");
        }

        final List<User> users = new UserFactory(players, scores).createUsers();

        if (users == null) {
            notifyError(new UiDataLoadError("Null users found", UiDataLoadError.ErrorKind.NO_DATA, false));
        } else {
            notifySuccess(users);
        }

    }
}
