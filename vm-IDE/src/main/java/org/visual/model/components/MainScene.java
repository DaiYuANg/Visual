package org.visual.model.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.VisualModelApplication;

@Slf4j
@ToString
public class MainScene {
    private double initializeWidth;

    private double initializeHeight;

    @Getter
    private Scene mainScene;

    private static final String fxml = "MainScene.fxml";

    public MainScene() {

        initDefaultWidthAndHeight();
        loadFXML();
    }

    private void initDefaultWidthAndHeight() {
        val bounds = Screen.getPrimary().getBounds();
        initializeWidth = bounds.getWidth() * 0.8;
        initializeHeight = bounds.getHeight() * 0.8;
    }

    @SneakyThrows
    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(VisualModelApplication.class.getResource(fxml));
        mainScene = new Scene(fxmlLoader.load(), initializeWidth, initializeHeight);
    }
}
