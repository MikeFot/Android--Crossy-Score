package com.michaelfotiadis.crossyscore.core.data.database.definitions;

/**
 *
 */
public abstract class DbColumn {

    private final String columnName;
    private final boolean primaryKey;

    /*package*/ DbColumn(final String columnName) {
        this(columnName, false);
    }

    /*package*/ DbColumn(final String columnName, final boolean primaryKey) {
        this.columnName = columnName;
        this.primaryKey = primaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public abstract DataType getDataType();

    public boolean isPrimaryKey() {
        return primaryKey;
    }
}
