package org.visual.app.repository;

import io.ebean.Database;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.entity.SavedState;

import java.util.List;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class SavedStateRepositoryImpl implements SavedStateRepository {
  private final Database database;

  @Override
  public List<SavedState> queryState() {
    return database.createQuery(SavedState.class).findList();
  }

  @Override
  public Uni<List<SavedState>> queryStateAsync() {
    return Uni.createFrom().item(queryState());
  }
}
