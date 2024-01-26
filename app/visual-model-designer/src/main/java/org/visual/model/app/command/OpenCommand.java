package org.visual.model.app.command;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "open", aliases = "o")
@Slf4j
public class OpenCommand implements Runnable {
    @Override
    public void run() {
        log.info("open");
    }
}
