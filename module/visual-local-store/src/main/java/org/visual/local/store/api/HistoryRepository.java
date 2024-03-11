package org.visual.local.store.api;

import java.util.Optional;
import org.visual.local.store.entity.History;

public interface HistoryRepository {
  Optional<History> findLatestHistory();

  void saveHistory(History history);
}
