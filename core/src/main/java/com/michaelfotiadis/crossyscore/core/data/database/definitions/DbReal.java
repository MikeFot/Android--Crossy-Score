package com.michaelfotiadis.crossyscore.core.data.database.definitions;

/**
 *
 */
public class DbReal extends DbColumn {

    public DbReal(final String columnName) {
        super(columnName);
    }

    public DbReal(final String columnName, final boolean primaryKey) {
        super(columnName, primaryKey);
    }

    @Override
    public DataType getDataType() {
        return DataType.REAL;
    }
}
