package org.visual.local.store.provider

import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import jakarta.inject.Inject
import jakarta.inject.Provider

class DatabaseProvider @Inject constructor(private val config: DatabaseConfig) :
    Provider<Database> {
  override fun get(): Database {
    return DatabaseFactory.create(config)
  }
}
