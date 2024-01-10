package org.visual.model.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.visual.model.ui.window.PanningWindow;

public class TestPanningWindow extends Application {
    private PanningWindow panningWindow;

    @Override
    public void start(@NotNull Stage stage) {
        this.panningWindow = new PanningWindow();
        stage.setScene(new Scene(new StackPane(panningWindow), 100, 100));
        stage.show();
    }
}
