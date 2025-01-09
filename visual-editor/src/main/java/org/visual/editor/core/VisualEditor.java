package org.visual.editor.core;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.data.structure.graph.Model;
import org.visual.data.structure.graph.Node;
import org.visual.data.structure.impl.VNode;
import org.visual.editor.api.CommandManager;
import org.visual.editor.api.Editor;
import org.visual.editor.command.AddCommand;
import org.visual.editor.command.CompoundCommand;
import org.visual.editor.command.DefaultCommandManager;
import org.visual.editor.view.EditorView;
import org.visual.editor.view.model.EditorProperties;

@Getter
@Slf4j
public class VisualEditor implements Editor {

  private final EditorView view;

  private final EditorProperties properties;

  private final CommandManager commandManager;

  private final ObjectProperty<Model> modelProperty = new SimpleObjectProperty<>();

  public VisualEditor(EditorProperties properties) {
    this.properties = properties;
    this.view = new EditorView(properties);
    this.commandManager = new DefaultCommandManager();
  }

  @Override
  public Region view() {
    return view;
  }

  public void addNode(Node node) {
    val addCommand = AddCommand.builder()
      .node(node)
      .model(getModel())
      .build();
    commandManager.executeCommand(addCommand);
  }

  public void removeNode(Node node) {
    val builder = CompoundCommand.builder();
  }

  public Model getModel() {
    return modelProperty.get();
  }

  public void setModel(Model model) {
    modelProperty.set(model);
  }
}
