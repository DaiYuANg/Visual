package org.visual.local.store.provider

import dev.dirs.BaseDirectories
import io.ebean.datasource.DataSourceConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Provider
import lombok.extern.slf4j.Slf4j

@Slf4j
class DataSourceConfigProvider : Provider<DataSourceConfig> {

  private val log = KotlinLogging.logger {}

  override fun get(): DataSourceConfig {
    val ds = DataSourceConfig()
    val url = "jdbc:h2:${BaseDirectories.get().dataLocalDir}visual.db"
    log.atInfo { message = "daltabase url:$url" }
    ds.setUsername("sa")
    ds.setPassword("")
    ds.setUrl(url)
    return ds
  }
}
