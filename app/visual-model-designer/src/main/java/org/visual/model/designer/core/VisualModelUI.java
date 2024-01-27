/* (C)2024*/
package org.visual.model.designer.core;

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
import org.visual.model.designer.constant.FXMLViews;
import org.visual.model.designer.context.ApplicationContext;
import org.visual.model.designer.handle.GlobalExceptionHandler;

@Slf4j
public class VisualModelUI extends Application {
    private final Parent rootFxml = ApplicationContext.INSTANCE.load(FXMLViews.MAIN_LAYOUT.getView());

    private final Scene rootScene = new Scene(rootFxml);

    private final Stage rootStage = ApplicationContext.INSTANCE.get(Stage.class);

    private final GlobalExceptionHandler exceptionHandler = ApplicationContext.INSTANCE.get(GlobalExceptionHandler.class);

    private final String theme;

    {
        theme = OsThemeDetector.getDetector().isDark()
                ? new PrimerDark().getUserAgentStylesheet()
                : new PrimerLight().getUserAgentStylesheet();
    }

    @Override
    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
        Application.setUserAgentStylesheet(theme);
//        rootScene.getStylesheets().addAll("/help.css", "/theme.css");
        rootScene.setFill(Color.TRANSPARENT);
    }

    @SneakyThrows
    @Override
    public void start(Stage stage) {
        rootStage.setScene(rootScene);
//        VisualModelDebugger.show(rootScene);
        rootStage.show();
//        FXComponentInspectorHandler.handleAll();
    }
}
