/* (C)2024*/
package org.visual.model.app;

import static java.lang.System.exit;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.app.command.OpenCommand;
import org.visual.model.app.core.VisualModelUI;
import picocli.CommandLine;

@CommandLine.Command(name = "VisualModel", mixinStandardHelpOptions = true, helpCommand = true,
        subcommands = OpenCommand.class
)
@RequiredArgsConstructor
@Slf4j
public class VisualModelDesigner implements Runnable {

    private final String[] args;

    @Override
    public void run() {
        Application.launch(VisualModelUI.class, args);
    }

    @SneakyThrows
    public static void main(String[] args) {
        log.atInfo().log("CommandLine Start");
        val commandLine = new VisualModelDesigner(args);
        val exitCode = new CommandLine(commandLine).execute(args);
        exit(exitCode);
    }
}
