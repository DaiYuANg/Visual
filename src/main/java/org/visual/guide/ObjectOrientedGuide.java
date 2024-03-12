package org.visual.guide;

import jakarta.inject.Singleton;
import java.util.function.Supplier;
import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;
import org.visual.constant.FXMLView;
import org.visual.context.UIContext;

@Singleton
public class ObjectOrientedGuide implements Guide {
  @Override
  public AvailableFeature feature() {
    return AvailableFeature.OBJECT_ORIENTED;
  }

  @Override
  public Supplier<Parent> guideView() {
    return () -> UIContext.INSTANCE.load(FXMLView.OBJECT_ORIENTED_GUIDE);
  }
}
