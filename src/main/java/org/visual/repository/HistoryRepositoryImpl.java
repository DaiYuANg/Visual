package org.visual.repository;

import io.ebean.Database;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.api.HistoryRepository;
import org.visual.entity.History;

import java.util.List;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
  private final Database database;

  @Override
  public List<History> queryHistory() {
    return database.createQuery(History.class).findList();
  }
}
