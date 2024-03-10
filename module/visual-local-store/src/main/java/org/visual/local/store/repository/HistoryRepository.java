package org.visual.local.store.repository;

import io.avaje.inject.PostConstruct;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.visual.local.store.entity.History;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepository {

  final SessionFactory sessionFactory;

  @PostConstruct
  void init() {
    //    database.save(new History().setPath("/"));
  }

  public Optional<History> findLatestHistory() {
    //    val query =
    //        new QHistory()
    //            .select(QHistory.alias().path)
    //            .orderBy()
    //            .select(QHistory.alias().createAt)
    //            .setMaxRows(1)
    //            .findOne();
    //    return Optional.ofNullable(query);
    return Optional.of(new History());
  }
}
