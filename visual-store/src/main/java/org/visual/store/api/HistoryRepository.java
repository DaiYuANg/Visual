package org.visual.store.api;

import java.util.List;
import org.visual.store.entity.History;

public interface HistoryRepository {
  List<History> queryHistory();
}
