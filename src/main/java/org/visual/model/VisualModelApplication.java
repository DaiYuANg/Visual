package org.visual.model;

import atlantafx.base.controls.Notification;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.components.MainScene;
import org.visual.model.contexts.ApplicationContext;
import org.visual.model.initializing.StageSetup;
import org.visual.model.initializing.VertxSetup;

@Slf4j
public class VisualModelApplication extends Application {
    private MainScene mainScene;

    @Override
    public void init() {
        new Notification();
        loadApplication();
        log.info("visual modeling starting");
        logging();
        ApplicationContext.CONTEXT.setI18nResource(ResourceBundle.getBundle("bundle", Locale.getDefault()));
        ApplicationContext.CONTEXT.setEventSubscriber(new EventSubscriber());
        val vs = new VertxSetup();
//        ApplicationContext.CONTEXT.setVertx(vs.getVertx());
//        ApplicationContext.CONTEXT.setEventBus(vs.getEventBus());
        this.mainScene = new MainScene();
    }

    @SneakyThrows
    private void loadApplication() {
        //        final OsThemeDetector detector = OsThemeDetector.getDetector();
        //        detector.registerListener(isDark -> {
        Platform.runLater(() -> {
            if (true) {
                Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            } else {
                Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
            }
        });
        //        });
        val p = new Properties();
        p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        p.forEach((k, v) -> System.setProperty(k.toString(), v.toString()));
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
        new StageSetup(stage, mainScene);
//        ApplicationContext.CONTEXT.getEventBus().publisher("test");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        log.info("visual modeling stop");
    }
}
