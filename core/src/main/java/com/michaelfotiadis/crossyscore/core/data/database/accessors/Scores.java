package com.michaelfotiadis.crossyscore.core.data.database.accessors;

import android.database.sqlite.SQLiteOpenHelper;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.ScoresContract;

import java.util.Collection;
import java.util.List;

/**
 * Created by michael
 */
public class Scores extends Accessor<Score> {

    private final ScoresContract mTable;

    public Scores(final SQLiteOpenHelper helper, final ScoresContract table) {
        super(helper, table.getTableName());
        this.mTable = table;
    }

    @Override
    public void clearData() {
        mTable.deleteAll(getWritableDb());
    }

    @Override
    public boolean delete(final List<String> ids) {
        return mTable.delete(getWritableDb(), ids);
    }

    @Override
    public List<Score> getAll() {
        return mTable.getAll(getReadableDb());
    }

    public List<Score> getForUserId(final String userId) {
        return mTable.getForUserId(getReadableDb(), userId);
    }

    @Override
    public Score getForId(final String id) {
        return getForIdInternal(mTable, id);
    }

    @Override
    public void upsert(final Collection<Score> items,
                       final InsertionContext<Score> insertionContext) {

        upsertCommon(mTable, items, insertionContext);
    }

    @Override
    public long upsert(final Score item, final InsertionContext<Score> insertionContext) {
        return upsertCommon(mTable, item, insertionContext);
    }
}
