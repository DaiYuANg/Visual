package org.visual.editor.command;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.visual.data.structure.graph.Model;
import org.visual.data.structure.graph.Node;
import org.visual.editor.api.Command;

import java.util.Objects;

@Builder
@Slf4j
public class AddCommand implements Command {

  private final Model model;
  private final Node node;

  @Override
  public boolean canExecute() {
    return Objects.nonNull(model);
  }

  @Override
  public void execute() {
    model.addNode(node);
  }

  @Override
  public void undo() {
    model.removeNode(node);
  }
}
