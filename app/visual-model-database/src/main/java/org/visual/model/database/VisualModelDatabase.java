package org.visual.model.database;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "vmd", helpCommand = true, mixinStandardHelpOptions = true)
@Slf4j
@RequiredArgsConstructor
public class VisualModelDatabase implements Runnable {

    private final String[] args;

    public static void main(String[] args) {
        new CommandLine(new VisualModelDatabase(args)).execute(args);
    }

    @Override
    public void run() {
        Application.launch(VisualDatabaseUI.class, args);
    }
}
