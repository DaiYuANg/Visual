package org.visual.model.language.cmd;

import picocli.CommandLine;

@CommandLine.Command
public class VMLCommand implements Runnable {
	@CommandLine.Option(names = "--interactive", interactive = true)
	String value;

	public static void main(String[] args) {
		new CommandLine(new VMLCommand()).execute(args);
	}

	@Override
	public void run() {
		if (value == null && System.console() != null) {
			// alternatively, use Console::readPassword
			value = System.console().readLine("Enter value for --interactive: ");
		}
		System.out.println("You provided value '" + value + "'");
	}
}
