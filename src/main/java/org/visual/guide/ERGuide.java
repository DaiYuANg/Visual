package org.visual.guide;

import jakarta.inject.Singleton;
import java.util.function.Supplier;
import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;
import org.visual.constant.FXMLView;
import org.visual.context.UIContext;

@Singleton
public class ERGuide implements Guide {
  @Override
  public AvailableFeature feature() {
    return AvailableFeature.ER;
  }

  @Override
  public Supplier<Parent> guideView() {
    return () -> UIContext.INSTANCE.load(FXMLView.ER_GUIDE);
  }
}
