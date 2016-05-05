package com.michaelfotiadis.crossyscore.core.data.database.contracts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbColumn;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbInteger;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbText;
import com.michaelfotiadis.crossyscore.core.data.parsers.CoreGson;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class ScoresContract extends DbTable<Score> {

    public static final String TABLE_NAME = "score";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_INSERTION_TIME = "insertion_time";

    public static final String SELECTION_BY_USER_ID = COL_USER_ID + "=?";

    private static final List<DbColumn> COLUMNS = joinWithBase(
            new DbText(COL_USER_ID),
            new DbInteger(COL_INSERTION_TIME)
    );

    public ScoresContract() {
    }

    @Override
    public List<Score> getAll(final SQLiteDatabase db) {
        final List<Score> result;

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
                    COL_INSERTION_TIME + " DESC");

            cursor.moveToFirst();
            final int jsonIndex = cursor.getColumnIndex(COL_JSON);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                result.add(CoreGson.get().fromJson(cursor.getString(jsonIndex), Score.class));
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

    public List<Score> getForUserId(final SQLiteDatabase db,
                                    final String userId) {

        final List<Score> results = new ArrayList<>();

        if (getMemoryCache().isPopulated()) {

            final List<Score> cachedItems = getAll(db);

            for (final Score score : cachedItems) {
                if (score.getOwnerId().equals(userId)) {
                    results.add(score);
                }
            }

        } else {

            final Cursor cursor = db.query(getTableName(),
                    new String[]{COL_JSON},
                    SELECTION_BY_USER_ID,
                    new String[]{userId},
                    null,
                    null,
                    null);

            results.addAll(extractCursorToList(cursor, Score.class));
        }

        return validateResults(results);


    }

    @Override
    public List<Score> getForId(final SQLiteDatabase db,
                                final String id) {

        final List<Score> results = new ArrayList<>();

        if (getMemoryCache().isPopulated()) {

            final List<Score> cachedItems = getAll(db);

            for (final Score event : cachedItems) {
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

            results.addAll(extractCursorToList(cursor, Score.class));
        }

        return validateResults(results);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void loadContentValue(final ContentValues cv,
                                 final Score item) {

        if (item == null) {
            throw new NullPointerException("Attempted to upsert null event");
        }

        cv.put(COL_USER_ID, item.getOwnerId());
        cv.put(COL_INSERTION_TIME, item.getTimeStamp() / 1000);
    }

    @Override
    protected void loadContentValue(final ContentValues cv,
                                    final Score item,
                                    final InsertionContext<Score> insertionContext) {

        loadContentValue(cv, item);
    }

    private static List<Score> validateResults(final List<Score> items) {
        final List<Score> validatedItems = new ArrayList<>();
        if (items != null) {
            for (final Score item : items) {
                if (item != null) {
                    validatedItems.add(item);
                }
            }
        }
        return validatedItems;
    }
}
