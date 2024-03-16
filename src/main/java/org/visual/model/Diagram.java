package org.visual.model;

import java.net.URI;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

@Data
@Builder
public class Diagram {
  private UUID projectId;

  @Builder.Default
  private Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
}
