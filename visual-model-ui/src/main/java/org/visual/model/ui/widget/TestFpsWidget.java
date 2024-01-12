package org.visual.model.ui.widget;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class TestFpsWidget extends Application {
    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        val p = new StackPane();
        p.getChildren().add(new FpsWidget());
        primaryStage.setScene(new Scene(p, 250, 150));
        primaryStage.show();
    }
}
