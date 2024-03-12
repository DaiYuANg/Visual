package org.visual.component.extension;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AnchorPaneUtil {
  public void setTopLeftAnchor(Node child) {
    AnchorPane.setTopAnchor(child, 0.0);
    AnchorPane.setLeftAnchor(child, 0.0);
  }

  public void setTopRightAnchor(Node child) {
    AnchorPane.setTopAnchor(child, 0.0);
    AnchorPane.setRightAnchor(child, 0.0);
  }

  public void setBottomRightAnchor(Node child) {
    AnchorPane.setBottomAnchor(child, 0.0);
    AnchorPane.setRightAnchor(child, 0.0);
  }

  public void setBottomLeftAnchor(Node child) {
    AnchorPane.setBottomAnchor(child, 0.0);
    AnchorPane.setLeftAnchor(child, 0.0);
  }
}
