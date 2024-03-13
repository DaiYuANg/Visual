package org.visual.local.store.repository;

import io.ebean.Database;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.local.store.api.HistoryRepository;
import org.visual.local.store.entity.History;
import org.visual.local.store.entity.query.QHistory;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HistoryRepositoryImpl implements HistoryRepository {

  final Database database;

  @Override
  public Optional<History> findLatestHistory() {
    val history = new QHistory().orderBy().createAt.desc().findOne();
    return Optional.empty();
  }
}
