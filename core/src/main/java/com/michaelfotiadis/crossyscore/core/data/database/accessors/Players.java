package com.michaelfotiadis.crossyscore.core.data.database.accessors;

import android.database.sqlite.SQLiteOpenHelper;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.core.data.database.additional.InsertionContext;
import com.michaelfotiadis.crossyscore.core.data.database.contracts.PlayersContract;

import java.util.Collection;
import java.util.List;

/**
 * Created by michael
 */
public class Players extends Accessor<Player> {

    private final PlayersContract mTable;

    public Players(final SQLiteOpenHelper helper, final PlayersContract table) {
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
    public List<Player> getAll() {
        return mTable.getAll(getReadableDb());
    }

    @Override
    public Player getForId(final String id) {
        return getForIdInternal(mTable, id);
    }

    @Override
    public void upsert(final Collection<Player> items,
                       final InsertionContext<Player> insertionContext) {

        upsertCommon(mTable, items, insertionContext);
    }

    @Override
    public long upsert(final Player item, final InsertionContext<Player> insertionContext) {
        return upsertCommon(mTable, item, insertionContext);
    }
}
