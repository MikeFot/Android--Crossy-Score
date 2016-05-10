package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class PlayerLoader extends DataFeedLoaderAbstract<Player> {

    public PlayerLoader(final Activity activity) {
        super(activity);
    }

    @Override
    public void loadData() {

        final List<Player> players = CrossyCore.getDataProvider().getPlayers().getAll();
        if (players == null) {
            notifyError(new UiDataLoadError("Null players found", UiDataLoadError.ErrorKind.NO_DATA, false));
        } else {
            AppLog.d("Loaded " + players.size() + " players");
            notifySuccess(players);
        }

    }
}
