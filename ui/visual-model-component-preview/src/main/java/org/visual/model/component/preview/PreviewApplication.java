package org.visual.model.component.preview;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.component.theme.OsThemeDetector;

@Slf4j
public class PreviewApplication extends Application {

    @Override
    public void init() {
        val theme = OsThemeDetector.getDetector().isDark()
                ? new PrimerDark().getUserAgentStylesheet()
                : new PrimerLight().getUserAgentStylesheet();
        Application.setUserAgentStylesheet(theme);
    }

    @SneakyThrows
    @Override
    public void start(@NotNull Stage stage) {
        Parent root = new FXMLLoader(PreviewApplication.class.getResource("Preview.fxml")).load();
        val scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
