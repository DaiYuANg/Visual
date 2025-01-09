package org.visual.app.context;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.data.structure.diagram.VDiagram;

import java.util.List;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DiagramContext {

  private final List<VDiagram> diagrams = new ObjectArrayList<>();

  public void add(VDiagram diagram) {
    diagrams.add(diagram);
  }
}
