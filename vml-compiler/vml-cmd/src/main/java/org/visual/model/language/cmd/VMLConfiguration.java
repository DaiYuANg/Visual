package org.visual.model.language.cmd;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

@CommandLine.Command(name = "config")
@Slf4j
public class VMLConfiguration implements Runnable {
	@Override
	public void run() {
		log.info("config");
	}
}
