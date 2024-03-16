package org.visual.model;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

@Data
@Builder
public class Project {

  @Builder.Default private UUID uuid = UUID.randomUUID();

  private String name;

  private Path path;

  @Singular private List<Diagram> diagrams;
}
