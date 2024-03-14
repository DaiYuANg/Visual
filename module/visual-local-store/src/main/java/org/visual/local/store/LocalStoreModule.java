package org.visual.local.store;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.persist.jpa.JpaPersistOptions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.local.store.api.ProjectRepository;
import org.visual.local.store.lifecycle.JPAInitializer;
import org.visual.local.store.provider.JpaQueryFactoryProvider;
import org.visual.local.store.repository.ProjectRepositoryImpl;

@Slf4j
public class LocalStoreModule extends AbstractModule {

  @Override
  protected void configure() {
    val option = JpaPersistOptions.builder().setAutoBeginWorkOnEntityManagerCreation(true).build();
    install(new JpaPersistModule("visualUnit", option));
    bind(JPAInitializer.class).asEagerSingleton();
    bind(JPAQueryFactory.class).toProvider(JpaQueryFactoryProvider.class);
    bind(ProjectRepository.class).to(ProjectRepositoryImpl.class);
  }
}
