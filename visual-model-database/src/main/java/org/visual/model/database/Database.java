package org.visual.model.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;
import org.visual.model.database.mapper.HikariMapper;
import org.visual.model.database.util.JDBCUtil;

import java.util.Set;

@ToString
public class Database implements org.visual.model.database.api.Database {
    private final HikariDataSource dataSource;

    public Database(DatabaseArgument argument) {
        val config = HikariMapper.INSTANCE.buildConfig(argument);
        this.dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    @Override
    public Set<DatabaseTableColumn> listTableColumns(String tableName) {
        return JDBCUtil.listColumns(dataSource.getConnection(), tableName);
    }
}
