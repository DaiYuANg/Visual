package org.visual.framework;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@Builder
@Slf4j
@CommandLine.Command
public abstract class VisualFramework extends Application implements Runnable {
    private String[] args;


    @Override
    public final void start(Stage stage) {

    }


    @Override
    public void run() {
        Application.launch(VisualFramework.class, args);
    }

    public <T extends VisualFramework> void start(T clazz, String[] args) {
        new CommandLine(clazz).execute(args);
    }
}