package org.visual.model.ui.layout;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class TestGird extends Application {
    @Override
    public void start(@NotNull Stage stage) {
        val g = new GirdBackground();
        g.setCache(true);
        val scene = new Scene(g);
        scene.getStylesheets().add("/window.css");
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setScene(scene);
        stage.show();
    }
}
