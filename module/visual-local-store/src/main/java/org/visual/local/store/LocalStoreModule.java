package org.visual.local.store;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.persist.jpa.JpaPersistOptions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.local.store.api.HistoryRepository;
import org.visual.local.store.lifecycle.Initializer;
import org.visual.local.store.repository.HistoryRepositoryImpl;

@Slf4j
public class LocalStoreModule extends AbstractModule {

  @Override
  protected void configure() {

    val option = JpaPersistOptions.builder().setAutoBeginWorkOnEntityManagerCreation(true).build();
    install(new JpaPersistModule("visualUnit", option));
    bind(Initializer.class).asEagerSingleton();
    bind(HistoryRepository.class).to(HistoryRepositoryImpl.class);
  }
}
