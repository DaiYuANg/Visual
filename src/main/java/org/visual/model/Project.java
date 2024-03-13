package org.visual.model;

import java.util.UUID;
import lombok.Data;
import org.immutables.value.Value;

@Data
@Value.Immutable
@Value.Style(jakarta = true, jdk9Collections = true)
public abstract class Project {

  @Value.Parameter
  public abstract UUID uuid();
}
