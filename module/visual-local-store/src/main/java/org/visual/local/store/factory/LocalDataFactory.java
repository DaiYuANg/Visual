package org.visual.local.store.factory;

import dev.dirs.BaseDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Factory
@Slf4j
public class LocalDataFactory {

  @Bean
  DataSourceConfig dataSourceConfig() {
    val ds = new DataSourceConfig();
    val url = STR."jdbc:h2:\{BaseDirectories.get().dataLocalDir}visual.db";
    log.info("Database URL at:{}", url);
    ds.setUsername("sa");
    ds.setPassword("");
    ds.setUrl(url);
    return ds;
  }

  @Bean
  DatabaseConfig databaseConfig(DataSourceConfig ds) {
    val config = new DatabaseConfig();
    config.setDataSourceConfig(ds);
    config.setRunMigration(true);
    return config;
  }

  @Bean
  Database database(DatabaseConfig config) {
    return DatabaseFactory.create(config);
  }
}
