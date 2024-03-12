package org.visual.guide;

import java.util.function.Supplier;
import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;

public interface Guide {

  AvailableFeature feature();

  Supplier<Parent> guideView();
}
