package org.visual.model.app;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Objects;
import java.util.concurrent.Executor;

import static org.visual.model.ui.util.StageInspector.inspect;

@Slf4j
@NoArgsConstructor
public class VisualModuleApplication extends Application {
//    private final Parent rootFxml = UIDIContainer.INSTANCE.load("MainLayout");

//    private final Scene rootScene = new Scene(rootFxml);

    private final Stage rootStage = UIDIContainer.INSTANCE.getInjector().getInstance(Stage.class);

    @Override
    public void init() {
//        rootScene.getStylesheets().addAll("/help.css", "/theme.css");
//        rootScene.setFill(Color.TRANSPARENT);
    }

    @Override
    public void start(Stage stage) {
//        rootStage.setScene(rootScene);
        val rootStage = DIContainer.INSTANCE.getScope().get(Stage.class);
        val t = (DIContainer.INSTANCE.getScope().get(Stage.class));
        System.err.println(DIContainer.INSTANCE.getScope().get(Executor.class));
        System.err.println(DIContainer.INSTANCE.getScope().get(Executor.class));
        System.err.println(Objects.deepEquals(rootStage,t));
        System.err.println(DIContainer.INSTANCE.getInjector().getInstance(Stage.class));
        System.err.println(DIContainer.INSTANCE.getInjector().getInstance(Stage.class));
        System.err.println(rootStage.equals(t));
        System.err.println(stage);
        log.info("start");
//        inspect(rootStage);
        rootStage.show();
    }

    public static void main(String[] args) {
        VisualModuleApplication.launch(args);
    }
}
