package org.visual.model.ui.base;

import javafx.scene.layout.HBox;
import javafx.stage.Window;

import java.util.Optional;

public class HHBox extends HBox {

    public Window getWindow() {
        return getScene().getWindow();
    }

    public Optional<Window> getWindowOptional() {
        return Optional.ofNullable(getWindow());
    }
}
