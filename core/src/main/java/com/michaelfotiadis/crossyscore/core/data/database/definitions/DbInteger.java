package com.michaelfotiadis.crossyscore.core.data.database.definitions;

/**
 *
 */
public class DbInteger extends DbColumn {

    public DbInteger(final String columnName) {
        super(columnName);
    }

    public DbInteger(final String columnName, final boolean primaryKey) {
        super(columnName, primaryKey);
    }

    @Override
    public DataType getDataType() {
        return DataType.INTEGER;
    }
}
