package com.michaelfotiadis.crossyscore.core.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.michaelfotiadis.crossyscore.core.data.database.contracts.DbTable;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.PlayersContract;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.ScoresContract;


/*protected*/ class CoreDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cinime_v2.db";
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    private static final DbTable[] TABLE_CONTRACTS = {
            new PlayersContract(),
            new ScoresContract()
    };

    public CoreDbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public final void close() {
        DbLog.d("close()");
        super.close();
    }

    public final void onCreate(final SQLiteDatabase db) {
        DbLog.d("onCreate()");
        for (final DbTable table : TABLE_CONTRACTS) {
            db.execSQL(table.getCreateTableStatement());
        }
    }

    public final void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        DbLog.d("onUpgrade()");
        recreate(db);
    }

    public final void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        DbLog.d("onDowngrade()");
        // This will throw an Exception
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public final void onOpen(final SQLiteDatabase db) {
        DbLog.d("onOpen()");
    }

    private void recreate(final SQLiteDatabase db) {
        for (final DbTable table : TABLE_CONTRACTS) {
            db.execSQL(table.getDropTableStatement());
        }

        onCreate(db);
    }

    @SuppressWarnings("MethodMayBeStatic")
    public DbTable[] getTableContracts() {
        return TABLE_CONTRACTS;
    }
}