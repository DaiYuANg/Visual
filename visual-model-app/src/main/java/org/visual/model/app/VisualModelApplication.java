package org.visual.model.app;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.app.command.OpenCommand;
import org.visual.model.app.factory.GuiceFactory;
import picocli.CommandLine;

@CommandLine.Command(name = "VisualModel", helpCommand = true,
        mixinStandardHelpOptions = true,
        subcommands = OpenCommand.class
)
@Slf4j
public class VisualModelApplication implements Runnable {
    private final static GuiceFactory guiceFactory = new GuiceFactory();

    public static void main(String[] args) {
        val exitCode = new CommandLine(new VisualModelApplication(), guiceFactory).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        log.info("run");
        Application.launch(VisualModelUIApplication.class);
    }
}
