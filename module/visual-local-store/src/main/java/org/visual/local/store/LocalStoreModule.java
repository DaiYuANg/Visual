package org.visual.local.store;

import com.google.inject.AbstractModule;
import io.ebean.Database;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.visual.local.store.api.HistoryRepository;
import org.visual.local.store.provider.DataSourceConfigProvider;
import org.visual.local.store.provider.DatabaseConfigProvider;
import org.visual.local.store.provider.DatabaseProvider;
import org.visual.local.store.repository.HistoryRepositoryImpl;

@Slf4j
public class LocalStoreModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(DataSourceConfig.class).toProvider(DataSourceConfigProvider.class).asEagerSingleton();
    bind(DatabaseConfig.class).toProvider(DatabaseConfigProvider.class).asEagerSingleton();
    bind(Database.class).toProvider(DatabaseProvider.class).asEagerSingleton();
    bind(HistoryRepository.class).to(HistoryRepositoryImpl.class);
  }
}
