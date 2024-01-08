package org.visual.model.language.cmd;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import picocli.CommandLine;

import java.util.concurrent.Executors;

@Slf4j
public class Main {
	public static void main(String[] args) {
		val cmd = new VMLCommand();
		val exitCode = new CommandLine(cmd).execute(args);
		System.exit(exitCode);
	}
}
