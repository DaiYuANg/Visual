package org.visual.app.factory;

import dev.dirs.ProjectDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.RequiresBean;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.smallrye.mutiny.Uni;
import lombok.val;

import java.nio.file.Paths;

@Factory
public class DBFactory {

  @Bean
  DataSourceConfig dataSourceConfig() {
    val dataSourceConfig = new DataSourceConfig();
    dataSourceConfig.setUsername("sa");
    dataSourceConfig.setPassword("");
    val databasePath = Paths.get(ProjectDirectories.fromPath("visual").dataDir, "visual.db");
    dataSourceConfig.setUrl("jdbc:h2:file:" + databasePath);
    return dataSourceConfig;
  }

  @Bean
  DatabaseConfig databaseConfig(DataSourceConfig dataSourceConfig) {
    val config = new DatabaseConfig();
    config.runMigration(true);
    config.setDataSourceConfig(dataSourceConfig);
    return config;
  }

  @Bean
  Uni<Database> sessionFactory(DatabaseConfig config) {
    return Uni.createFrom().item(DatabaseFactory.create(config));
  }
}