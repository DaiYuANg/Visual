package org.visual.command;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "open", aliases = "o")
@Slf4j
@Singleton
public class OpenCommand implements Runnable {

  @Override
  public void run() {
    log.info("open");
  }
}
