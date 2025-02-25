package org.visual.editor.command;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.data.structure.graph.Node;
import org.visual.editor.api.Command;

import java.util.Objects;

@Slf4j
@Builder
public class RemoveConnectionCommand implements Command {

  private final Node node;

  @Override
  public void execute() {
    val connections = node.connectors().stream().flatMap(connector -> connector.connections().stream().filter(Objects::nonNull)).distinct().toList();
    connections.stream().filter(connection -> {
      val source = connection.source();
      val target = connection.target();
      return !node.equals(source.parent()) && !node.equals(target.parent());
    }).toList();
  }

  @Override
  public void undo() {

  }
}
