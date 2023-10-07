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
public class StageInitializer implements Initializer {

    @Inject
    private Stage stage;

    @Override
    public void initialize() {
        setOnClose();
        setView();
    }

    private void setOnClose() {
        stage.setOnCloseRequest(event -> {
            val alert = new Alert(Alert.AlertType.CONFIRMATION);
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
		stage.show();
    }
}
