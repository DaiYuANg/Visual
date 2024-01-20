/* (C)2024*/
package org.visual.model.app.core;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.app.handle.GlobalExceptionHandler;
import picocli.CommandLine;

@CommandLine.Command(name = "VisualModel", mixinStandardHelpOptions = true, helpCommand = true)
@RequiredArgsConstructor
@Slf4j
public class VisualModelApplication implements Runnable {

    private final String[] args;

    static {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
    }

    @Override
    public void run() {
        Application.launch(VisualModelUI.class, args);
    }

    @SneakyThrows
    public static void main(String[] args) {
        log.atInfo().log("CommandLine Start");
        val commandLine = new VisualModelApplication(args);
        val exitCode = new CommandLine(commandLine).execute(args);
        System.exit(exitCode);
    }
}
