package org.visual.model.language.cmd;

import static picocli.CommandLine.*;

import lombok.extern.slf4j.Slf4j;

@Command(subcommands = {VMLBuild.class,
		VMLFormatter.class}, description = "Visual Model Language Command Line", helpCommand = true, mixinStandardHelpOptions = true)
@Slf4j
public class VMLCommand implements Runnable {

	@Override
	public void run() {
	}
}
