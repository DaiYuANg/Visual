package org.visual.app.component;

import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
public class EditorContainer extends AnchorPane {

  private final GridBackground gridBackground = new GridBackground();

  public EditorContainer() {
    getChildren().add(gridBackground);
  }

  @Override
  protected void layoutChildren() {
    super.layoutChildren();
    val width = getWidth();
    val height = getHeight();
    gridBackground.resizeRelocate(0, 0, width, height);
  }
}
