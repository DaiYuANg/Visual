package org.visual.local.store.repository;

import io.avaje.inject.PostConstruct;
import io.ebean.Database;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.local.store.entity.History;
import org.visual.local.store.entity.query.QHistory;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepository {

  final Database database;

  @PostConstruct
  void init() {
    database.save(new History().setPath("/"));
  }

  public Optional<History> findLatestHistory() {
    val query =
        new QHistory()
            .select(QHistory.alias().path)
            .orderBy()
            .select(QHistory.alias().createAt)
            .setMaxRows(1)
            .findOne();
    return Optional.ofNullable(query);
  }
}
