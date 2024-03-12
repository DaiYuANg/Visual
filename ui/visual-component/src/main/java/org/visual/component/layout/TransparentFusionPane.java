package org.visual.component.layout;

import javafx.scene.paint.Color;

public class TransparentFusionPane extends FusionPane {
  public TransparentFusionPane() {
    super();
  }

  public TransparentFusionPane(boolean manuallyHandleOuterRegion) {
    super(manuallyHandleOuterRegion);
  }

  @Override
  protected AbstractFusionPane buildRootNode() {
    return new TransparentFusionPaneImpl();
  }

  protected class TransparentFusionPaneImpl extends FusionPaneImpl {
    @Override
    protected Color normalColor() {
      return Color.TRANSPARENT;
    }

    @Override
    protected Color hoverColor() {
      return Color.TRANSPARENT;
    }
  }
}
