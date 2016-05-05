package com.michaelfotiadis.crossyscore.core.data.database.accessors;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.core.data.database.DbLog;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.DbTable;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public abstract class Accessor<T extends AppModel> {

    private final SQLiteOpenHelper mHelper;
    private final String mTableName;

    protected Accessor(final SQLiteOpenHelper helper,
                       final String tableName) {
        mHelper = helper;
        mTableName = tableName;
    }

    public abstract void clearData();

    public abstract boolean delete(final List<String> ids);

    public abstract List<T> getAll();

    public abstract T getForId(final String id);

    protected T getForIdInternal(final DbTable<T> table, final String id) {
        final List<T> results = table.getForId(getReadableDb(), id);

        if (results == null || results.size() == 0) {
            return null;
        } else {
            return results.get(0);
        }

    }

    protected final SQLiteDatabase getReadableDb() {
        return mHelper.getReadableDatabase();
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(getReadableDb(), mTableName);
    }

    public final void upsert(final Collection<T> items) {
        upsert(items, null);
    }

    public abstract void upsert(final Collection<T> items, final InsertionContext<T> insertionContext);

    public final long upsert(final T item) {
        return upsert(item, null);
    }

    public abstract long upsert(final T item, final InsertionContext<T> insertionContext);

    protected long upsertCommon(final DbTable<T> table, final T item, final InsertionContext<T> insertionContext) {
        DbLog.d("Received single item to upsert");
        final SQLiteDatabase db = getWritableDb();

        final boolean weStartTransaction = db.inTransaction();

        long rowId = -1;

        if (weStartTransaction) {
            try {
                db.beginTransaction();
                rowId = upsertInternal(db, table, item, insertionContext);
                db.setTransactionSuccessful();
            } catch (final Exception e) {
                DbLog.w("Error writing to db: " + e.getLocalizedMessage());
            } finally {
                db.endTransaction();
            }
        } else {
            rowId = upsertInternal(db, table, item, insertionContext);
        }

        return rowId;
    }

    protected SQLiteDatabase getWritableDb() {
        return mHelper.getWritableDatabase();
    }

    private long upsertInternal(final SQLiteDatabase db,
                                final DbTable<T> table,
                                final T item,
                                final InsertionContext<T> insertionContext) {

        final long rowId;
        rowId = table.upsert(db, item, insertionContext);
        return rowId;
    }

    protected void upsertCommon(final DbTable<T> table,
                                final Collection<T> items,
                                final InsertionContext<T> insertionContext) {
        DbLog.d("Received " + items.size() + " items to upsert");
        final SQLiteDatabase db = getWritableDb();
        final boolean weStartTransaction = db.inTransaction();
        if (weStartTransaction) {
            try {
                db.beginTransaction();
                upsertInternal(db, table, items, insertionContext);
                db.setTransactionSuccessful();
            } catch (final Exception e) {
                DbLog.w("Error writing to db: " + e.getLocalizedMessage());
            } finally {
                db.endTransaction();
            }
        } else {
            upsertInternal(db, table, items, insertionContext);
        }
    }

    private void upsertInternal(final SQLiteDatabase db,
                                final DbTable<T> table,
                                final Collection<T> items,
                                final InsertionContext<T> insertionContext) {

        for (final T item : items) {
            //DbLog.d("Upserting item " + item);
            upsertInternal(db, table, item, insertionContext);
        }
    }
}
