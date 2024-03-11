package org.visual.local.store.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class JpaQueryFactoryProvider implements Provider<JPAQueryFactory> {

  private final EntityManager entityManager;

  @Override
  public JPAQueryFactory get() {
    log.info("Entity Manager:{}", entityManager);
    return new JPAQueryFactory(entityManager);
  }
}
