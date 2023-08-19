package org.visual.model.language.cmd;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "format",aliases = "fmt")
@Slf4j
public class VMLFormatter implements Runnable{
    @Override
    public void run() {

    }
}
