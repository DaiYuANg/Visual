package org.visual.component.window;

import static org.visual.component.extension.AnchorPaneExtensions.*;

import java.util.function.Supplier;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BorderLessPane extends AnchorPane {
  private final Stage stage;

  private final Supplier<AnchorPane> topLeftPane =
      () -> {
        AnchorPane pane = createPane();
        setTopLeftAnchor(pane);
        pane.setCursor(Cursor.NW_RESIZE);
        return pane;
      };

  private final Supplier<AnchorPane> topRightPane =
      () -> {
        AnchorPane pane = createPane();
        setTopRightAnchor(pane);
        pane.setCursor(Cursor.NE_RESIZE);
        return pane;
      };

  private final Supplier<AnchorPane> bottomRightPane =
      () -> {
        AnchorPane pane = createPane();
        setBottomRightAnchor(pane);
        pane.setCursor(Cursor.SE_RESIZE);
        return pane;
      };

  private final Supplier<AnchorPane> bottomLeftPane =
      () -> {
        AnchorPane pane = createPane();
        setBottomLeftAnchor(pane);
        pane.setCursor(Cursor.W_RESIZE);
        return pane;
      };

  private final SimpleBooleanProperty maximized = new SimpleBooleanProperty(false);
  private final SimpleBooleanProperty resizable = new SimpleBooleanProperty(true);
  private final SimpleBooleanProperty snap = new SimpleBooleanProperty(true);

  private boolean snapped = false;

  public BorderLessPane(Stage stage) {
    this.stage = stage;
  }

  private AnchorPane createPane() {
    return new AnchorPane();
  }
}
