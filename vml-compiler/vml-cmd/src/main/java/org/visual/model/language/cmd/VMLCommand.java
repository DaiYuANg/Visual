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

	public static void main(String[] args) {
		val exitCode = new CommandLine(new VMLCommand()).execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
	}
}
