package org.visual.database;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.database.context.VisualDatabaseContext;

@RequiredArgsConstructor
@Slf4j
public class VisualDatabaseUI extends Application {

    private final Stage rootStage = VisualDatabaseContext.INSTANCE.get(Stage.class);

    private final Parent layout = VisualDatabaseContext.INSTANCE.load("Layout");

    private final Scene rootScene = new Scene(layout);

    {
        rootStage.setScene(rootScene);
    }

    @Override
    public void start(Stage primaryStage) {
        rootStage.show();
    }
}
