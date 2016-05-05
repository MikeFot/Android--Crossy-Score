package com.michaelfotiadis.crossyscore.core.data.database.contracts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbColumn;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbInteger;
import com.michaelfotiadis.crossyscore.core.data.parsers.gson.CoreGson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class PlayersContract extends DbTable<Player> {

    public static final String TABLE_NAME = "player";
    private static final String COL_REGISTERED_ON = "registered_on";

    private static final List<DbColumn> COLUMNS = joinWithBase(
            new DbInteger(COL_REGISTERED_ON)
    );

    public PlayersContract() {
    }

    @Override
    public List<Player> getAll(final SQLiteDatabase db) {
        final List<Player> result;

        if (getMemoryCache().isPopulated()) {
            result = getMemoryCache().get();
        } else {
            result = new ArrayList<>();
            final Cursor cursor = db.query(
                    getTableName(),
                    new String[]{COL_JSON},
                    null,
                    null,
                    null,
                    null,
                    COL_REGISTERED_ON + " DESC");

            cursor.moveToFirst();
            final int jsonIndex = cursor.getColumnIndex(COL_JSON);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                result.add(CoreGson.get().fromJson(cursor.getString(jsonIndex), Player.class));
            }
            cursor.close();
            getMemoryCache().set(result);
        }

        return validateResults(result);
    }

    @Override
    public List<DbColumn> getColumns() {
        return COLUMNS;
    }

    @Override
    public List<Player> getForId(final SQLiteDatabase db,
                                 final String id) {

        final List<Player> results = new ArrayList<>();

        if (getMemoryCache().isPopulated()) {

            final List<Player> cachedItems = getAll(db);

            for (final Player event : cachedItems) {
                if (String.valueOf(event.getId()).equals(id)) {
                    results.add(event);
                }
            }

        } else {

            final Cursor cursor = db.query(getTableName(),
                    new String[]{COL_JSON},
                    DbTable.SELECTION_BY_ID,
                    new String[]{id},
                    null,
                    null,
                    null);

            results.addAll(extractCursorToList(cursor, Player.class));
        }

        return validateResults(results);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void loadContentValue(final ContentValues cv,
                                 final Player item) {

        if (item == null) {
            throw new NullPointerException("Attempted to upsert null event");
        }

        cv.put(COL_REGISTERED_ON, item.getRegisteredOn() / 1000);
    }

    @Override
    protected void loadContentValue(final ContentValues cv,
                                    final Player item,
                                    final InsertionContext<Player> insertionContext) {

        loadContentValue(cv, item);
    }

    private static List<Player> validateResults(final List<Player> items) {
        final List<Player> validatedItems = new ArrayList<>();
        if (items != null) {
            for (final Player item : items) {
                if (item != null) {
                    validatedItems.add(item);
                }
            }
        }
        return validatedItems;
    }
}
