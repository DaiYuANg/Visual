package org.visual.model.database.driver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;

@RequiredArgsConstructor
@Getter
public enum Driver {
    MYSQL(new DefaultArtifact("", "", "", "")),

    POSTGRESQL(new DefaultArtifact("", "", "", ""));

    private final Artifact artifact;
}
