package org.visual.editor.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Window;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.data.structure.graph.GraphicalObject;


/**
 * The rectangle that is drawn when dragging on the view to create a selection.
 */
public class SelectionBox extends Rectangle {

  private static final String STYLE_CLASS_SELECTION_BOX = "graph-editor-selection-box";

  private ChangeListener<Window> windowListener;
  private ChangeListener<Boolean> windowFocusListener;

  /**
   * Creates a new {@link SelectionBox} instance.
   */
  public SelectionBox() {
    getStyleClass().addAll(STYLE_CLASS_SELECTION_BOX);

    setVisible(false);
    setManaged(false);
    setMouseTransparent(true);

    addWindowFocusListener();
  }

  public void draw(
    @NotNull GraphicalObject graphicalObject
  ) {
    setVisible(true);
    setX(graphicalObject.getX());
    setY(graphicalObject.getY());
    setWidth(graphicalObject.getWidth());
    setHeight(graphicalObject.getHeight());
  }

  /**
   * Adds a listener to the focused property of the selection box's window, so
   * that the selection box always disappears when the window loses focus.
   *
   * <p>
   * If this listener is not added, the selection box will not disappear if
   * the user alt-tabs before releasing the right-mouse button.
   * </p>
   */
  private void addWindowFocusListener() {
    val sceneListener = new ChangeListener<Scene>() {
      @Override
      public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
        if (oldValue != null) {
          oldValue.windowProperty().removeListener(windowListener);
        }

        if (newValue != null) {
          newValue.windowProperty().addListener(windowListener);
        }
      }
    };
    windowListener = (observable, oldValue, newValue) ->
    {
      if (oldValue != null) {
        oldValue.focusedProperty().removeListener(windowFocusListener);
      }

      if (newValue != null) {
        newValue.focusedProperty().addListener(windowFocusListener);
      }
    };

    windowFocusListener = (observable, oldValue, newValue) ->
    {
      if (!newValue) {
        setVisible(false);
      }
    };

    sceneProperty().addListener(sceneListener);
  }
}
