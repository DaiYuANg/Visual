/* (C)2024*/
package org.visual;

import static java.lang.System.exit;

import io.vavr.control.Try;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.command.CompileCommand;
import org.visual.command.OpenCommand;
import org.visual.view.VisualUI;
import picocli.CommandLine;

@CommandLine.Command(
    name = "Visual",
    mixinStandardHelpOptions = true,
    helpCommand = true,
    subcommands = {OpenCommand.class, CompileCommand.class})
@RequiredArgsConstructor
@Slf4j
public class VisualApplication implements Runnable {

  private final String[] args;

  @SneakyThrows
  @Override
  public void run() {

    Try.run(
        () -> {
          log.atInfo().log("启动");
        });
    Application.launch(VisualUI.class, args);
  }

  @SneakyThrows
  public static void main(String[] args) {
    log.atDebug().log("Visual Start");
    val commandLine = new VisualApplication(args);
    val exitCode = new CommandLine(commandLine).execute(args);
    exit(exitCode);
  }
}
