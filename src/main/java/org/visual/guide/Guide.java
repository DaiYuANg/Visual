package org.visual.guide;

import javafx.scene.Parent;
import org.visual.constant.AvailableFeature;

public interface Guide {

  AvailableFeature feature();

  Parent guideView();
}
