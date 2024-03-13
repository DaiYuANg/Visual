package org.visual.guide;

import jakarta.inject.Singleton;
import java.util.function.Supplier;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import org.visual.constant.AvailableFeature;

@Singleton
public class ObjectOrientedGuide implements Guide {
  @Override
  public AvailableFeature feature() {
    return AvailableFeature.OBJECT_ORIENTED;
  }

  @Override
  public Supplier<Pane> guideView() {
    return FlowPane::new;
  }
}
