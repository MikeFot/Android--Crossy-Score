package com.michaelfotiadis.crossyscore.core.data.database.contracts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;
import com.michaelfotiadis.crossyscore.common.models.base.WithStringId;
import com.michaelfotiadis.crossyscore.core.data.database.DbLog;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.cache.InMemoryCache;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbColumn;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbInteger;
import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbText;
import com.michaelfotiadis.crossyscore.core.data.parsers.CoreGson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public abstract class DbTable<T extends AppModel> {

    public static final String COL_ID = "id";
    public static final String COL_JSON = "json";
    public static final String SELECTION_BY_ID = COL_ID + "=?";
    private static final String COL_LOCAL_LAST_MODIFIED = "last_modified_local";
    private static final List<DbColumn> BASE_COLUMNS = getUnmodifiable(
            new DbText(COL_ID, true),
            new DbInteger(COL_LOCAL_LAST_MODIFIED),
            new DbText(COL_JSON)
    );

    private final InMemoryCache<T> mMemoryCache;

    protected DbTable() {
        this.mMemoryCache = new InMemoryCache<>();
    }

    private static List<DbColumn> getUnmodifiable(final DbColumn... columns) {
        return Collections.unmodifiableList(Arrays.asList(columns));
    }

    protected static List<DbColumn> joinWithBase(final DbColumn... columns) {
        final List<DbColumn> result = new ArrayList<>(BASE_COLUMNS);
        result.addAll(Arrays.asList(columns));
        return Collections.unmodifiableList(result);
    }

    public boolean delete(final SQLiteDatabase db, final List<String> ids) {

        if (ids.isEmpty()) {
            DbLog.w("No ids received to delete");
            return false;
        }

        final String[] array = ids.toArray(new String[ids.size()]);

        DbLog.d("Deleting list of ids: " + Arrays.toString(array));

        final String in = " IN (" + new String(new char[array.length - 1]).replace("\0", "?,") + "?)";

        DbLog.d("Deletion In statement= " + in);

        final int result = db.delete(getTableName(),
                DbTable.COL_ID + in,
                array);

        DbLog.d("Deleted: " + result + " items in table " + getTableName());
        if (result > 0) {
            mMemoryCache.clear();
            return true;
        } else {
            return false;
        }

    }

    public abstract String getTableName();

    public final void deleteAll(final SQLiteDatabase db) {
        db.delete(getTableName(), null, null);
        mMemoryCache.clear();
    }

    protected final List<T> extractCursorToList(final Cursor cursor, final Class<T> clazz) {
        final List<T> result = new ArrayList<>();
        cursor.moveToFirst();

        final int jsonIndex = cursor.getColumnIndex(COL_JSON);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            result.add(CoreGson.get().fromJson(cursor.getString(jsonIndex), clazz));
        }

        cursor.close();
        return result;
    }

    public abstract List<T> getAll(final SQLiteDatabase db);

    @SuppressWarnings("unused")
    public final String[] getColumnsAsString() {
        return DbTableUtils.getColumnsAsString(getColumns());
    }

    protected abstract List<DbColumn> getColumns();

    public final String getCreateTableStatement() {
        return DbTableUtils.getCreateTableStatement(getTableName(), getColumns());
    }

    public final String getDropTableStatement() {
        return DbTableUtils.getDropTableStatement(getTableName());
    }

    public abstract List<T> getForId(final SQLiteDatabase db, final String id);

    /*package*/ InMemoryCache<T> getMemoryCache() {
        return mMemoryCache;
    }

    protected abstract void loadContentValue(final ContentValues cv, final T item);

    public final void upsert(final SQLiteDatabase db, final Collection<T> items) {
        upsert(db, items, null);
    }

    public final void upsert(final SQLiteDatabase db,
                             final Collection<T> items,
                             final InsertionContext<T> insertionContext) {

        final ContentValues cv = new ContentValues();

        for (final T item : items) {
            cv.clear();
            loadContentValueInternal(cv, item, insertionContext);
            upsertInternal(db, cv);
        }
    }

    private void loadContentValueInternal(final ContentValues cv, final T item, final InsertionContext<T> insertionContext) {

        if (item instanceof WithLongId) {
            cv.put(COL_ID, ((WithLongId) item).getId());
        } else if (item instanceof WithStringId) {
            cv.put(COL_ID, ((WithStringId) item).getId());
        }

        cv.put(COL_LOCAL_LAST_MODIFIED, System.currentTimeMillis());
        cv.put(COL_JSON, CoreGson.get().toJson(item));
        loadContentValue(cv, item, insertionContext);
    }

    private long upsertInternal(final SQLiteDatabase db, final ContentValues cv) {
        final Object id = cv.get(COL_ID);

        if (id == null) {
            DbLog.d("Content with null id " + cv);
            throw new IllegalStateException("ID cannot be null! (" + getTableName() + ")");
        }

        final long rowId = db.insertWithOnConflict(getTableName(), null, cv, SQLiteDatabase.CONFLICT_REPLACE);

        DbLog.d("Updating item in '" + getTableName() + "' with ID '" + id + "'. RowId=" + rowId);

        mMemoryCache.clear();

        return rowId;
    }

    protected abstract void loadContentValue(final ContentValues cv, final T item, final InsertionContext<T> insertionContext);

    public final long upsert(final SQLiteDatabase db, final T item) {
        return upsert(db, item, null);
    }

    public final long upsert(final SQLiteDatabase db,
                             final T item,
                             final InsertionContext<T> insertionContext) {

        //DbLog.d("Upserting one item in table '" + getTableName() + "'");

        final ContentValues cv = new ContentValues();
        loadContentValueInternal(cv, item, insertionContext);
        return upsertInternal(db, cv);
    }
}
