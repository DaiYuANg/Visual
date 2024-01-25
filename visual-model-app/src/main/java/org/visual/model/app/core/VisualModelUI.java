/* (C)2024*/
package org.visual.model.app.core;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.component.theme.OsThemeDetector;
import org.visual.model.debugger.VisualModelDebugger;
import org.visual.model.debugger.inspector.FXComponentInspectorHandler;

@Slf4j
public class VisualModelUI extends Application {
    private final Parent rootFxml = VisualModelAppContext.INSTANCE.load("MainLayout");

    private final Scene rootScene = new Scene(rootFxml);

    private final Stage rootStage = VisualModelAppContext.INSTANCE.get(Stage.class);

    private final String theme;

    {
        theme = OsThemeDetector.getDetector().isDark()
                ? new PrimerDark().getUserAgentStylesheet()
                : new PrimerLight().getUserAgentStylesheet();
    }

    @Override
    public void init() {
        Application.setUserAgentStylesheet(theme);
        rootScene.getStylesheets().addAll("/help.css", "/theme.css");
        rootScene.setFill(Color.TRANSPARENT);
    }

    @SneakyThrows
    @Override
    public void start(Stage stage) {
        rootStage.setScene(rootScene);
        VisualModelDebugger.show(rootScene);
        rootStage.show();
        FXComponentInspectorHandler.handleAll();
    }
}
