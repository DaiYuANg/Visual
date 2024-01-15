package org.visual.model.ui;

import javafx.geometry.Rectangle2D;
import lombok.Getter;

@Getter
public class ScreenConstant {
    public static final Rectangle2D primaryScreen = javafx.stage.Screen.getPrimary().getVisualBounds();

}
