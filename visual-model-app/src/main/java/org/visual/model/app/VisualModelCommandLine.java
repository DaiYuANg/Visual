package org.visual.model.app;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.shared.ManifestReader;
import picocli.CommandLine;

@CommandLine.Command(name = "VisualModel", mixinStandardHelpOptions = true, helpCommand = true)
@RequiredArgsConstructor
@Slf4j
public class VisualModelCommandLine implements Runnable {

    private final String[] args;

    @Override
    public void run() {
        Application.launch(VisualModelApplication.class, args);
    }

    public static void main(String[] args) {
        String javafxVersion = System.getProperty("javafx.runtime.version");
        System.err.println(javafxVersion);
        val m = new ManifestReader();
        log.atInfo().log("CommandLine Start");
        val commandLine = new VisualModelCommandLine(args);
        val exitCode = new CommandLine(commandLine).execute(args);
        System.exit(exitCode);
    }
}
