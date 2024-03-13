package org.visual.guide;

import java.util.function.Supplier;
import javafx.scene.layout.Pane;
import org.visual.constant.AvailableFeature;

public interface Guide {

  AvailableFeature feature();

  Supplier<Pane> guideView();
}
