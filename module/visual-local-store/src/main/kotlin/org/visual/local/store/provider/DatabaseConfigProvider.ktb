package org.visual.local.store.provider

import io.ebean.config.DatabaseConfig
import io.ebean.datasource.DataSourceConfig
import jakarta.inject.Inject
import jakarta.inject.Provider

class DatabaseConfigProvider @Inject constructor(private val datasourceConfig: DataSourceConfig) :
    Provider<DatabaseConfig> {
  override fun get(): DatabaseConfig {
    val config = DatabaseConfig()
    config.setDataSourceConfig(datasourceConfig)
    config.isRunMigration = true
    return config
  }
}
