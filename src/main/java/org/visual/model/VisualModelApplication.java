package org.visual.model;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.components.MainScene;
import org.visual.model.initializing.StageSetup;
import org.visual.model.initializing.VertxSetup;

@Slf4j
public class VisualModelApplication extends Application {
    private MainScene mainScene;

    @Override
    public void init() {
        loadApplication();
        log.info("visual modeling starting");
        logging();
        ApplicationContext.CONTEXT.setI18nResource(ResourceBundle.getBundle("bundle", Locale.getDefault()));
        ApplicationContext.CONTEXT.setEventSubscriber(new EventSubscriber());
        val vs = new VertxSetup();
        ApplicationContext.CONTEXT.setVertx(vs.getVertx());
        ApplicationContext.CONTEXT.setEventBus(vs.getEventBus());
        this.mainScene = new MainScene();
    }

    @SneakyThrows
    private void loadApplication() {
        val p = new Properties();
        p.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        p.forEach((k, v) -> System.setProperty(k.toString(), v.toString()));
        System.err.println(System.getProperties());
    }

    private void logging() {
        log.info("Current User: " + System.getProperty("user.name"));
    }

    @Override
    public void start(@NotNull Stage stage) {
        new StageSetup(stage, mainScene);
        ApplicationContext.CONTEXT.getEventBus().publisher("test");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() {
        log.info("visual modeling stop");
    }
}
