package org.visual.component.control.button;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.visual.component.theme.Theme;

public class TransparentFusionImageButton extends FusionImageButton {
  public TransparentFusionImageButton() {}

  public TransparentFusionImageButton(Image image) {
    super(image);
  }

  @Override
  protected Color normalColor() {
    return Theme.current().transparentFusionButtonNormalBackgroundColor();
  }

  @Override
  protected Color hoverColor() {
    return Theme.current().transparentFusionButtonHoverBackgroundColor();
  }

  @Override
  protected Color downColor() {
    return Theme.current().transparentFusionButtonDownBackgroundColor();
  }
}
