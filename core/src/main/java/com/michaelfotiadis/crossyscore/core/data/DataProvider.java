package com.michaelfotiadis.crossyscore.core.data;

import android.content.Context;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.core.data.database.CoreDatabase;
import com.michaelfotiadis.crossyscore.core.data.database.accessors.Players;
import com.michaelfotiadis.crossyscore.core.data.database.accessors.Scores;
import com.michaelfotiadis.crossyscore.core.data.parsers.mascots.MascotsParser;

import java.util.List;

/**
 *
 */
public class DataProvider {


    private final MascotsParser mMascotsParser;

    public DataProvider(final Context context) {
        CoreDatabase.setContext(context);
        mMascotsParser = new MascotsParser(context);

    }

    @SuppressWarnings("MethodMayBeStatic")
    public Players getPlayers() {
        return CoreDatabase.getInstance().getPlayers();
    }

    @SuppressWarnings("MethodMayBeStatic")
    public Scores getScores() {
        return CoreDatabase.getInstance().getScores();
    }

    public List<Mascot> getMascots() {
        return mMascotsParser.getMascots();
    }


}
