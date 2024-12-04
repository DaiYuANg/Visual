package org.visual.api;

import java.util.List;
import org.visual.entity.History;

public interface HistoryRepository {
  List<History> queryHistory();
}
