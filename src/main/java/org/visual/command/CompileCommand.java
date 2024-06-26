package org.visual.command;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "compile")
@Slf4j
public class CompileCommand implements Runnable {
  @Override
  public void run() {}
}
