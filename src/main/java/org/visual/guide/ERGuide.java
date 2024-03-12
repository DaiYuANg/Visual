package org.visual.guide;

import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;
import org.visual.constant.FXMLView;
import org.visual.context.UIContext;

public class ERGuide implements Guide {
  @Override
  public AvailableFeature feature() {
    return AvailableFeature.ER;
  }

  @Override
  public Parent guideView() {
    return UIContext.INSTANCE.load(FXMLView.ER_GUIDE);
  }
}
