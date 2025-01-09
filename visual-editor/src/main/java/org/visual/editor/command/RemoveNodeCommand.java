package org.visual.editor.command;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.visual.data.structure.graph.Model;
import org.visual.data.structure.graph.Node;
import org.visual.editor.api.Command;

@Builder
@Slf4j
public class RemoveNodeCommand implements Command {

  private final Model model;

  private final Node node;

  @Override
  public void execute() {
    model.removeNode(node);
  }

  @Override
  public void undo() {
    model.addNode(node);
  }
}
