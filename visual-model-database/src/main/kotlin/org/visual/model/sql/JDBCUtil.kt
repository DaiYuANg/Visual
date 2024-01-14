@file:JvmName("JDBCUtil")

package org.visual.model.sql

import java.sql.Connection

fun listDatabase(conn: Connection): Set<String> {
    val metadata = conn.metaData
    val c = metadata.catalogs
    return generateSequence {
        c
    }.takeWhile { c.next() }.map {
        c.getString(DatabaseResultKey.TABLE_CAT.key)
    }.toSet()
}

fun listTables(conn: Connection): Set<String> {
    val metadata = conn.metaData
    val catalog: String = conn.catalog
    val tables = metadata.getTables(catalog, null, null, arrayOf("TABLE"))
    return generateSequence {
        tables
    }.takeWhile { tables.next() }
        .map { tables.getString(3) }
        .toSet()
}

fun listColumns(conn: Connection, tableName: String): Set<DatabaseTableColumn> {
    val getColumns = conn.metaData.getColumns(conn.catalog, null, tableName, null)
    return generateSequence { getColumns }
        .takeWhile { it.next() }
        .map {
            DatabaseTableColumn.builder()
                .columnName(it.getString(DatabaseResultKey.COLUMN_NAME.key))
                .typeName(it.getString(DatabaseResultKey.TYPE_NAME.key))
                .columnSize(it.getInt(DatabaseResultKey.COLUMN_SIZE.key))
                .nullable(it.getInt(DatabaseResultKey.NULLABLE.key))
                .build()
        }
        .toSet()
}

fun listPrimaryKeys(conn: Connection, tableName: String): Set<String> {
    val metaData = conn.metaData
    val getPrimaryKey = metaData.getPrimaryKeys(conn.catalog, null, tableName)
    return generateSequence { getPrimaryKey }
        .takeWhile { it.next() }
        .map { it.getString(DatabaseResultKey.COLUMN_NAME.key) }
        .toSet()
}

fun listIndexes(conn: Connection, tableName: String): Map<String, List<String>> {
    val metaData = conn.metaData
    val getIndexInfo = metaData.getIndexInfo(null, null, tableName, false, true)
    return generateSequence { getIndexInfo }
        .takeWhile { it.next() }
        .fold(mutableMapOf()) { acc, resultSet ->
            val indexName = resultSet.getString(DatabaseResultKey.INDEX_NAME.key)
            val columnName = resultSet.getString(DatabaseResultKey.COLUMN_NAME.key)
            acc.computeIfAbsent(indexName) { mutableListOf() }.addFirst(columnName)
            acc
        }
}