package org.visual.shared.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class JavaFxProperty {
  private String version;
  private String runtimeVersion;
  private int runtimeBuild;
}
