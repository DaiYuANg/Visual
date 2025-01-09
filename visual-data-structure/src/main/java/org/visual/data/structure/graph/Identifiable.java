package org.visual.data.structure.graph;

import org.immutables.value.Value;

import java.util.UUID;

public interface Identifiable {
  default String id() {
    return UUID.randomUUID().toString();
  }
}
