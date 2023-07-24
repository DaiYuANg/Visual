package org.visual.model.views;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.utils.FxmlLoaderHelper;

@Slf4j
@ToString
public enum MainScene {
    MAIN_SCENE;

    private double initializeWidth;

    private double initializeHeight;

    @Getter
    private Scene mainScene;

    private static final String fxml = "MainScene";

    MainScene() {

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
        mainScene = new Scene(FxmlLoaderHelper.load(fxml), initializeWidth, initializeHeight);
        mainScene.setFill(Color.TRANSPARENT);
    }
}
