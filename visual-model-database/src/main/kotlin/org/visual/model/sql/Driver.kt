package org.visual.model.sql

import org.eclipse.aether.artifact.Artifact
import org.eclipse.aether.artifact.DefaultArtifact

enum class Driver(val artifact: Artifact) {
    MYSQL(DefaultArtifact("", "", "", "")),

    POSTGRESQL(DefaultArtifact("", "", "", ""))
}