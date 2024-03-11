package org.visual.local.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.local.store.entity.History;
import org.visual.local.store.entity.QHistory;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class HistoryRepository {

  private final JPAQueryFactory jpaQueryFactory;

  private final QHistory qHistory = QHistory.history;

  public Optional<History> findLatestHistory() {
    return Optional.ofNullable(jpaQueryFactory.select(qHistory).from(qHistory).fetchOne());
  }
}
