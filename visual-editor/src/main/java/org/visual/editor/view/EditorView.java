package org.visual.editor.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.editor.view.model.EditorProperties;

import static org.visual.editor.constant.Style.STYLE_CLASS;

@Slf4j
public class EditorView extends Region {

  @Getter
  private final EditorProperties editorProperties;
  private final Pane nodeLayer = new NodeLayer();
  private final Pane connectionLayer = new ConnectionLayer();
  private final SelectionBox mSelectionBox = new SelectionBox();
  private final GridBackground gridBackground = new GridBackground();

  public EditorView(@NotNull EditorProperties properties) {
    this.editorProperties = properties;
    getStyleClass().addAll(STYLE_CLASS);
    setMaxWidth(editorProperties.getMaxWidth());
    setMaxHeight(editorProperties.getMaxHeight());
    gridBackground.visibleProperty().bind(properties.getGridVisibleProperty());
    gridBackground.getSpacing().bind(properties.getGridSpacingProperty());
  }

  public void clear() {
    nodeLayer.getChildren().clear();
    connectionLayer.getChildren().clear();
  }
}
