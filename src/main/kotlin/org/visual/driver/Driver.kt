/* (C)2024*/
package org.visual.driver

import lombok.Getter
import lombok.RequiredArgsConstructor
import org.eclipse.aether.artifact.Artifact
import org.eclipse.aether.artifact.DefaultArtifact

@RequiredArgsConstructor
@Getter
enum class Driver(val artifact: Artifact) {
  MYSQL(DefaultArtifact("", "", "", "")),
  POSTGRESQL(DefaultArtifact("", "", "", ""))
}
