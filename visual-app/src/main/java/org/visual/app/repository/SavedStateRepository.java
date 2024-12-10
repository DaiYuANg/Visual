package org.visual.app.repository;

import io.smallrye.mutiny.Uni;
import org.visual.app.entity.SavedState;

import java.util.List;

public interface SavedStateRepository {
  List<SavedState> queryState();
  Uni<List<SavedState>> queryStateAsync();
}
