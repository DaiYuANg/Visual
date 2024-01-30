package org.visual.component.control;

import javafx.scene.image.Image;
import javafx.scene.layout.CornerRadii;
import org.visual.component.container.VStage;
import org.visual.component.container.VStageInitParams;

public class IconifyButton extends WindowControlButton {
  public IconifyButton(VStage stage, VStageInitParams initParams) {
    super(stage, initParams);
  }

  @Override
  protected void onMouseClicked() {
    stage.setIconified(true);
  }

  @Override
  protected CornerRadii getCornerRadii() {
    return new CornerRadii(0, 0, 0, 4, false);
  }

  @Override
  protected Image getNormalImage() {
    return null;
  }

  @Override
  protected Image getHoverImage() {
    return null;
  }
}
