package org.visual.collaboration.server.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record HttpConfig(
  Integer port
) {
}
