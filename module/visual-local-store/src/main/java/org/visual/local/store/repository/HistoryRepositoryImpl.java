package org.visual.local.store.repository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.local.store.api.HistoryRepository;
import org.visual.local.store.entity.History;

@Singleton
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HistoryRepositoryImpl implements HistoryRepository {

  final EntityManager entityManager;

  @Override
  public Optional<History> findLatestHistory() {
    return Optional.empty();
  }
}
