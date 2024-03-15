package org.visual.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

  private UUID uuid;

  private Path path;

  private Set<Diagram> diagrams;
}
