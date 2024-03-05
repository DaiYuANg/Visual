/* (C)2024*/
package org.visual.jdbc.util;

import java.sql.Connection;
import java.util.*;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.jdbc.core.DatabaseResultKey;
import org.visual.jdbc.core.DatabaseTableColumn;

@UtilityClass
public class JDBCUtil {

  @SneakyThrows
  public static @NotNull Set<String> listDatabase(@NonNull Connection conn) {
    val metadata = conn.getMetaData();
    val c = metadata.getCatalogs();
    val result = new HashSet<String>();
    while (c.next()) {
      result.add(c.getString(DatabaseResultKey.TABLE_CAT.getKey()));
    }
    return result;
  }

  @SneakyThrows
  public static @NotNull Set<String> listTables(@NonNull Connection conn) {
    val metadata = conn.getMetaData();
    val catalog = conn.getCatalog();
    val tables = metadata.getTables(catalog, null, null, new String[] {"TABLE"});
    val result = new HashSet<String>();
    while (tables.next()) {
      result.add(tables.getString(3));
    }
    return result;
  }

  @SneakyThrows
  public static @NotNull Set<DatabaseTableColumn> listColumns(
      @NonNull Connection conn, @NonNull String tableName) {
    val getColumns = conn.getMetaData().getColumns(conn.getCatalog(), null, tableName, null);
    val result = new HashSet<DatabaseTableColumn>();
    while (getColumns.next()) {
      val col =
          DatabaseTableColumn.builder()
              .columnName(getColumns.getString(DatabaseResultKey.COLUMN_NAME.getKey()))
              .typeName(getColumns.getString(DatabaseResultKey.TYPE_NAME.getKey()))
              .columnSize(getColumns.getInt(DatabaseResultKey.COLUMN_SIZE.getKey()))
              .nullable(getColumns.getInt(DatabaseResultKey.NULLABLE.getKey()))
              .build();
      result.add(col);
    }
    return result;
  }

  @SneakyThrows
  public static @NotNull Set<String> listPrimaryKeys(
      @NonNull Connection conn, @NonNull String tableName) {
    val getPrimaryKey = conn.getMetaData().getPrimaryKeys(conn.getCatalog(), null, tableName);
    val result = new HashSet<String>();
    while (getPrimaryKey.next()) {
      result.add(getPrimaryKey.getString(DatabaseResultKey.COLUMN_NAME.getKey()));
    }
    return result;
  }

  @SneakyThrows
  public Map<String, List<String>> listIndexes(@NotNull Connection conn, String tableName) {
    val metaData = conn.getMetaData();
    val getIndexInfo = metaData.getIndexInfo(null, null, tableName, false, true);

    Map<String, List<String>> result = new HashMap<>();

    while (getIndexInfo.next()) {
      String indexName = getIndexInfo.getString(DatabaseResultKey.INDEX_NAME.getKey());
      String columnName = getIndexInfo.getString(DatabaseResultKey.COLUMN_NAME.getKey());
      result.computeIfAbsent(indexName, k -> new ArrayList<>()).addFirst(columnName);
    }

    return result;
  }
}
