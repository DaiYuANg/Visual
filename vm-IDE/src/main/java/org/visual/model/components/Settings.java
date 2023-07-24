package org.visual.model.components;

import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.utils.FxmlLoaderHelper;

@Slf4j
public enum Settings {

    SETTINGS;

    @Getter
    private final Scene settingsScene;
    private static final String fxml = "Settings";

    Settings() {
        this.settingsScene = new Scene(FxmlLoaderHelper.load(fxml));
    }

    public void openSettings(){
        val settings = new Stage();
        settings.setScene(settingsScene);
        val bounds = Screen.getPrimary().getBounds();
        settings.setWidth(bounds.getWidth() * 0.5);
        settings.setHeight(bounds.getHeight() * 0.65);
        settings.setTitle("Settings");
        log.info("open settings");
        settings.show();
    }
}
