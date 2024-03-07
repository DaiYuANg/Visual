package org.visual.component.display;

import javafx.scene.input.MouseButton;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.visual.component.control.FontAwesomeSolidButton;

public class MacOSTitleBar extends CommonTitleBar {
  private final FontAwesomeSolidButton closeButton =
      new FontAwesomeSolidButton(FontAwesomeSolid.BARS);
  private final FontAwesomeSolidButton sizeableButton =
      new FontAwesomeSolidButton(FontAwesomeSolid.MINUS);
  private final FontAwesomeSolidButton minimizeButton =
      new FontAwesomeSolidButton(FontAwesomeSolid.CLOSED_CAPTIONING);

  public MacOSTitleBar() {
    closeButton.setOnAction(event -> close());

    sizeableButton.setOnMouseClicked(
        event -> {
          if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            restoreSizeOrMax();
          } else {
            maximize();
          }
        });

    minimizeButton.setOnAction(event -> maximize());
    getChildren().addAll(closeButton, sizeableButton, minimizeButton);
  }
}
