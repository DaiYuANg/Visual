package org.visual.app.command;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "compile")
@Slf4j
@Singleton
public class CompileCommand implements Runnable {
  @Override
  public void run() {}
}
