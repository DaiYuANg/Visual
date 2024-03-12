/* (C)2024*/
package org.visual;

import static java.lang.System.exit;

import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.command.DIFactory;
import org.visual.command.OpenCommand;
import org.visual.view.VisualUI;
import picocli.CommandLine;

@CommandLine.Command(
    name = "VisualModel",
    mixinStandardHelpOptions = true,
    helpCommand = true,
    subcommands = OpenCommand.class)
@RequiredArgsConstructor
@Slf4j
public class VisualApplication implements Runnable {

  private final String[] args;

  @Override
  public void run() {
    Application.launch(VisualUI.class, args);
  }

  @SneakyThrows
  public static void main(String[] args) {
    log.atDebug().log("Visual Start");
    val commandLine = new VisualApplication(args);
    val exitCode = new CommandLine(commandLine, new DIFactory()).execute(args);
    exit(exitCode);
  }
}
