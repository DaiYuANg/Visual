/* (C)2024*/
package org.visual.jdbc.core;

import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;
import org.visual.jdbc.api.VMDatabase;
import org.visual.jdbc.mapper.HikariMapper;
import org.visual.jdbc.util.JDBCUtil;

@ToString
public class Database implements VMDatabase {
  private final HikariDataSource dataSource;

  public Database(DatabaseArgument argument) {
    //    new org.visual.jdbc.MySqlParserBaseListener();
    val config = HikariMapper.INSTANCE.buildConfig(argument);
    this.dataSource = new HikariDataSource(config);
  }

  @SneakyThrows
  @Override
  public Set<DatabaseTableColumn> listTableColumns(String tableName) {
    return JDBCUtil.listColumns(dataSource.getConnection(), tableName);
  }

  @SneakyThrows
  @Override
  public Set<String> listDatabase() {
    return JDBCUtil.listDatabase(dataSource.getConnection());
  }

  @SneakyThrows
  public Set<String> listTables() {
    return JDBCUtil.listTables(dataSource.getConnection());
  }

  @SneakyThrows
  public Set<String> listPrimaryKey(String tableName) {
    return JDBCUtil.listPrimaryKeys(dataSource.getConnection(), tableName);
  }

  @SneakyThrows
  @Override
  public Map<String, List<String>> listIndexes(String tableName) {
    return JDBCUtil.listIndexes(dataSource.getConnection(), tableName);
  }
}
