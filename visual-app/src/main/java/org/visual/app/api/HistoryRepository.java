package org.visual.app.api;

import io.smallrye.mutiny.Uni;
import org.visual.app.entity.History;

import java.util.List;

public interface HistoryRepository {
  Uni<List<History>> queryHistory();
}
