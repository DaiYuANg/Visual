package org.visual.guide;

import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;
import org.visual.constant.FXMLView;
import org.visual.context.UIContext;

public class ObjectOrientedGuide implements Guide {
  @Override
  public AvailableFeature feature() {
    return AvailableFeature.OBJECT_ORIENTED;
  }

  @Override
  public Parent guideView() {
    return UIContext.INSTANCE.load(FXMLView.OBJECT_ORIENTED_GUIDE);
  }
}
