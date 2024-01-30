package org.visual.component.control.button;

import javafx.scene.paint.Color;
import org.visual.component.container.AbstractFusionPane;

public abstract class AbstractFusionButton extends AbstractFusionPane {
  protected abstract void onMouseClicked();

  @Override
  protected Color normalColor() {
    return Color.TRANSPARENT;
  }

  @Override
  protected Color hoverColor() {
    return Color.TRANSPARENT;
  }

  @Override
  protected Color downColor() {
    return Color.TRANSPARENT;
  }
}
