package org.visual.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.inject.Singleton;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.store.api.HistoryRepository;
import org.visual.store.entity.History;
import org.visual.store.entity.QHistory;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<History> queryHistory() {
    return jpaQueryFactory.selectFrom(QHistory.history).fetch();
  }
}
