package org.visual.local.store.repository;

import com.google.inject.persist.Transactional;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.local.store.api.HistoryRepository;
import org.visual.local.store.entity.History;
import org.visual.local.store.entity.QHistory;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class HistoryRepositoryImpl implements HistoryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  private final EntityManager entityManager;

  private final QHistory qHistory = QHistory.history;

  @Override
  public Optional<History> findLatestHistory() {
    return Optional.ofNullable(jpaQueryFactory.select(qHistory).from(qHistory).fetchOne());
  }

  @Override
  @Transactional
  public void saveHistory(History history) {
    entityManager.persist(history);
  }
}
