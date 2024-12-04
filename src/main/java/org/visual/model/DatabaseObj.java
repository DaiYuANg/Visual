package org.visual.model;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record DatabaseObj(
  String name,
  String type
) {
}
