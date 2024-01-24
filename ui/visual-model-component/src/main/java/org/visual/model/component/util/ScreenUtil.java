package org.visual.model.component.util;

import javafx.geometry.Rectangle2D;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ScreenUtil {
    public static final Rectangle2D primaryScreen = javafx.stage.Screen.getPrimary().getVisualBounds();

    @Contract("_ -> new")
    public static @NotNull Pair<Double, Double> percentOfScreen(double percent) {
        return Pair.of(primaryScreen.getWidth() * percent, primaryScreen.getHeight() * percent);
    }
}
