package org.visual.debugger;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.factory.GuiceFactory;
import org.visual.debugger.view.VisualDebuggerView;
import picocli.CommandLine;

@RequiredArgsConstructor
@Slf4j
@CommandLine.Command(name = "VisualDebugger",
        helpCommand = true,
        mixinStandardHelpOptions = true
)
public class VisualDebugger implements Runnable {

    private final String[] args;

    @Override
    public void run() {
        Application.launch(VisualDebuggerView.class, args);
    }

    public static void main(String[] args) {
        new CommandLine(new VisualDebugger(args), new GuiceFactory()).execute(args);
    }
}
