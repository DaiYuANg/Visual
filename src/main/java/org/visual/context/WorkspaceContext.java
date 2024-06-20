package org.visual.context;

import io.soabase.recordbuilder.core.RecordBuilder;
import java.nio.file.Path;

@RecordBuilder
public record WorkspaceContext(String id, Path path) {}
