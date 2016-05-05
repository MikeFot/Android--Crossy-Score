package com.michaelfotiadis.crossyscore.core.data;

import android.content.Context;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.core.data.database.CoreDatabase;
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
    public CoreDatabase getDatabase() {
        return CoreDatabase.getInstance();
    }

    public List<Mascot> getMascots() {
        return mMascotsParser.getMascots();
    }


}
