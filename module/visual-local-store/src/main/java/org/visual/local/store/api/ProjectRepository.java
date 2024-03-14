package org.visual.local.store.api;

import java.util.Optional;
import org.visual.local.store.entity.Project;

public interface ProjectRepository {

  Optional<Project> findLatestEdited();
}
