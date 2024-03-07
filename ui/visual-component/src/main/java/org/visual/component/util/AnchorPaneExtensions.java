package org.visual.component.extension;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class AnchorPaneExtensions {
  public static void setTopLeftAnchor(Node child) {
    AnchorPane.setTopAnchor(child, 0.0);
    AnchorPane.setLeftAnchor(child, 0.0);
  }

  public static void setTopRightAnchor(Node child) {
    AnchorPane.setTopAnchor(child, 0.0);
    AnchorPane.setRightAnchor(child, 0.0);
  }

  public static void setBottomRightAnchor(Node child) {
    AnchorPane.setBottomAnchor(child, 0.0);
    AnchorPane.setRightAnchor(child, 0.0);
  }

  public static void setBottomLeftAnchor(Node child) {
    AnchorPane.setBottomAnchor(child, 0.0);
    AnchorPane.setLeftAnchor(child, 0.0);
  }
}
