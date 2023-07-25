package org.visual.model.mvc.views;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import lombok.Getter;
import lombok.val;
import org.visual.model.utils.FxmlLoaderHelper;

public enum MainLayout {
    INSTANCE;

    private final String fxml = "MainLayout";

    private final Parent parent = FxmlLoaderHelper.load(fxml);

    private final double initializeWidth = Screen.getPrimary().getBounds().getWidth() * 0.8;
    private final double initializeHeight = Screen.getPrimary().getBounds().getHeight() * 0.8;

    @Getter
    private Scene scene = new Scene(parent, initializeWidth, initializeHeight);

    MainLayout() {
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
    }
}
