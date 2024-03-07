package org.visual.command;

import jakarta.inject.Inject;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "open", aliases = "o")
@Slf4j
public class OpenCommand implements Runnable {

  @Inject Stage stage;

  @Override
  public void run() {
    log.info("open");
  }
}
