package org.visual.model.initializing;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Singleton
public class StageInitializer implements IStageInitializer {

    @Inject
    private Stage stage;

    @Override
    public void init() {
        System.err.println(stage);
        setOnClose();
        setView();
    }

    private void setOnClose() {
        stage.setOnCloseRequest(event -> {
            val alert = new Alert(Alert.AlertType.CONFIRMATION);
            // alert.setTitle(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM.getValue()));
            // alert.setHeaderText(I18nContext.I18N.getI18nResource(I18nKeys.CONFIRM_CLOSE_HEADER.getValue()));
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
//		 MFXThemeManager.addOn(stage.getScene(), Themes.DEFAULT, Themes.LEGACY);
//		stage.show();
    }
}
