package org.visual.app.repository;

import io.ebean.Database;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.api.HistoryRepository;
import org.visual.app.entity.History;

import java.util.List;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
  private final Uni<Database> database;

  @Override
  public Uni<List<History>> queryHistory() {
    return database.map(db -> db.createQuery(History.class).findList());
  }
}
