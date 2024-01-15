package org.visual.model.ui.bar;

import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.visual.model.shared.OperationSystem;

import java.util.function.Supplier;

import static org.visual.model.shared.SystemUtil.detect;
import static org.visual.model.ui.ScreenConstant.primaryScreen;

public class CommonTitleBar extends HBox {
    private double xOffset = 0.0;
    private double yOffset = 0.0;

    private double prevWidth;
    private double prevHeight;

    private final Supplier<Stage> stage = () -> (Stage) getScene().getWindow();

    {
        setPadding(new Insets(0.0, 0.0, 0.0, 0.0));
    }

    {
        addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            getScene().getWindow().setX(e.getSceneX() - xOffset);
            getScene().getWindow().setY(e.getSceneY() - yOffset);
        });
        addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            getScene().getWindow().setOpacity(0.5);
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, e -> getScene().getWindow().setOpacity(1.0));
        addEventHandler(KeyEvent.KEY_TYPED, e -> {
            if (e.getCode().isFunctionKey() && e.getCode() == KeyCode.F11) {
                max((Stage) getScene().getWindow());
            }
        });
    }

    public void close() {
        if (detect() == OperationSystem.MAC) {
            stage.get().setIconified(true);
            return;
        }
        stage.get().close();
    }

    public void maximizeWindow() {
        max(stage.get());
    }

    public void minimizeWindow() {
        stage.get().setIconified(true);
    }

    public void restoreSizeOrMax() {
        if (prevWidth == 0.0 && prevHeight == 0.0) {
            prevWidth = getScene().getWidth();
            prevHeight = getScene().getHeight();
            max(stage.get());
        } else {
            stage.get().setWidth(prevWidth);
            stage.get().setHeight(prevHeight);
        }
    }

    public void max(@NotNull Stage stage) {
        stage.setX(primaryScreen.getMinX());
        stage.setY(primaryScreen.getMinY());
        stage.setWidth(primaryScreen.getWidth());
        stage.setHeight(primaryScreen.getHeight());
    }
}
