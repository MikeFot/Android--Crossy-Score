package com.michaelfotiadis.crossyscore.core.data.database;

import android.content.Context;

import com.michaelfotiadis.crossyscore.core.data.database.accessors.Scores;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.DbTable;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.ScoresContract;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public final class CoreDatabase {

    private static CoreDatabase sInstance;
    private final CoreDbHelper mHelper;

    private final Scores mScores;

    private CoreDatabase(final Context context) {
        mHelper = new CoreDbHelper(context);

        final Map<String, DbTable> map = new HashMap<>();

        for (final DbTable table : mHelper.getTableContracts()) {
            map.put(table.getTableName(), table);
        }

        mScores = new Scores(mHelper, (ScoresContract) map.get(ScoresContract.TABLE_NAME));

    }

    public static CoreDatabase getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("You must call setContext before getInstance!");
        }
        return sInstance;
    }

    public static void setContext(final Context context) {
        if (sInstance == null) {
            sInstance = new CoreDatabase(context.getApplicationContext());
        }
    }

    public void forceOpen() {
        mHelper.getReadableDatabase();
    }

    public Scores getScores() {
        return mScores;
    }
}
