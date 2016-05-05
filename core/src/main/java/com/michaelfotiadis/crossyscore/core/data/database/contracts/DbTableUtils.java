package com.michaelfotiadis.crossyscore.core.data.database.contracts;

import com.michaelfotiadis.crossyscore.core.data.database.definitions.DbColumn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
/*package*/ final class DbTableUtils {

    private DbTableUtils() {
    }

    public static String[] getColumnsAsString(final List<DbColumn> columns) {
        final List<String> items = new ArrayList<>();

        for (final DbColumn column : columns) {
            items.add(column.getColumnName());
        }

        return items.toArray(new String[items.size()]);
    }

    public static String getCreateTableStatement(final String tableName,
                                                 final List<DbColumn> columns) {

        final StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(tableName);
        sb.append(" (");

        boolean firstRun = true;
        for (final DbColumn column : columns) {
            if (!firstRun) {
                sb.append(", ");
            }

            sb.append(column.getColumnName());
            sb.append(" ");
            sb.append(column.getDataType());
            if (column.isPrimaryKey()) {
                sb.append(" PRIMARY KEY");
            }

            firstRun = false;
        }

        sb.append(");");

        return sb.toString();
    }

    public static String getDropTableStatement(final String tableName) {
        return "DROP TABLE IF EXISTS " + tableName + ";";
    }
}
