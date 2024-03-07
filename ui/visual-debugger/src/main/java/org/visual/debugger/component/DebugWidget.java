package org.visual.debugger.component;

import static org.visual.component.util.GeometryUtil.makeSameInsets;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.visual.component.widget.FpsWidget;

public class DebugWidget extends StackPane {

  private final Background selfBackground;

  public DebugWidget() {
    selfBackground =
        new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.5), null, makeSameInsets(0.5)));

    FpsWidget fpsWidget = new FpsWidget();
    getChildren().add(fpsWidget);
    setBackground(selfBackground);
    toFront();
  }
}
