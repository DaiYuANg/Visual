package org.visual.model.setup;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.contexts.I18nContext;
import org.visual.model.i18n.I18nKeys;

@Slf4j
public class StageSetup {
    private final Stage stage;

    public StageSetup(Stage stage) {
        this.stage = stage;
        setOnClose();
        setView();
    }

    private void setOnClose() {
        stage.setOnCloseRequest(event -> {
            val alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM.getValue()));
            alert.setHeaderText(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM_CLOSE_HEADER.getValue()));
            if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                stage.close();
                return;
            }
            event.consume();
        });
    }

    private void setView() {
        stage.centerOnScreen();
        stage.setTitle(System.getProperty("application.name"));
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            val alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        });
//        stage.setScene(mainScene);
//        MFXThemeManager.addOn(mainScene, Themes.DEFAULT, Themes.LEGACY);
        stage.show();
    }
}
