package org.visual.app.api;

import java.util.List;
import org.visual.app.entity.History;

public interface HistoryRepository {
  List<History> queryHistory();
}
