package org.visual.editor.command;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.visual.data.structure.graph.Node;
import org.visual.editor.api.Command;

@Slf4j
@Builder
public class RemoveConnectionCommand implements Command {

  private final Node node;

  @Override
  public void execute() {
  }

  @Override
  public void undo() {

  }
}
