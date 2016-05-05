package com.michaelfotiadis.crossyscore.core.data.database.definitions;

/**
 *
 */
public class DbText extends DbColumn {

    public DbText(final String columnName) {
        super(columnName);
    }

    public DbText(final String columnName, final boolean primaryKey) {
        super(columnName, primaryKey);
    }

    @Override
    public DataType getDataType() {
        return DataType.TEXT;
    }
}
