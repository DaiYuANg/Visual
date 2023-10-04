package org.visual.model.language.cmd;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;

import static picocli.CommandLine.*;

@Command(
		subcommands = {
				VMLBuild.class,
				VMLFormatter.class
		},
		description = "Visual Model Language Command Line",
		helpCommand = true,
		mixinStandardHelpOptions = true
)
@Slf4j
public class VMLCommand implements Runnable {

	@Override
	public void run() {

	}
}
