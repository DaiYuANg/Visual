/* (C)2024*/
import static org.visual.model.database.util.JDBCUtil.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers(parallel = true)
public class TestJDBCUtil {

    @Container
    MySQLContainer<?> mySQLContainer =
            new MySQLContainer<>("mysql:latest").withDatabaseName("test_mysql").withInitScript("init-mysql.sql");

    @Container
    PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest").withDatabaseName("test_postgresql");

    HikariDataSource mysqlDs;

    HikariDataSource postgresqlDs;

    @BeforeEach
    void init() {
        mySQLContainer.start();
        val config = new HikariConfig();
        config.setJdbcUrl(mySQLContainer.getJdbcUrl());
        config.setUsername(mySQLContainer.getUsername());
        config.setPassword(mySQLContainer.getPassword());
        mysqlDs = new HikariDataSource(config);
    }

    @BeforeEach
    void initPostgresSQL() {
        postgreSQLContainer.start();
        val config = new HikariConfig();
        config.setJdbcUrl(postgreSQLContainer.getJdbcUrl());
        config.setUsername(postgreSQLContainer.getUsername());
        config.setPassword(postgreSQLContainer.getPassword());
        postgresqlDs = new HikariDataSource(config);
    }

    @SneakyThrows
    @Test
    void testMysqlDatabase() {
        val database = listDatabase(mysqlDs.getConnection());
        log.info("mysql databases:{}", database);
        Assertions.assertTrue(database.contains("test_mysql"));
    }

    @SneakyThrows
    @Test
    void testMysqlTables() {
        val tables = listTables(mysqlDs.getConnection());
        log.info("tables:{}", tables);
    }

    @SneakyThrows
    @Test
    void testTableColumns() {
        val columns = listColumns(mysqlDs.getConnection(), "test");
        log.info("mysql columns:{}", columns);
    }

    @SneakyThrows
    @Test
    void testPostgresqlDatabase() {
        val database = listDatabase(postgresqlDs.getConnection());
        log.info("postgresql databases:{}", database);
        Assertions.assertTrue(database.contains("test_postgresql"));
    }

    @AfterEach
    void clear() {
        mysqlDs.close();
        postgresqlDs.close();
        postgreSQLContainer.close();
        mySQLContainer.stop();
    }
}
