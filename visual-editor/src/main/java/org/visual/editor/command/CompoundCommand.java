package org.visual.editor.command;

import lombok.Builder;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.visual.editor.api.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Builder
public class CompoundCommand implements Command {

  @Singular
  private final List<Command> commands;

  private final List<Command> executedCommands = new ArrayList<>();

  @Override
  public boolean canExecute() {
    return commands.stream().allMatch(Command::canExecute);
  }

  @Override
  public void execute() {
    if (!canExecute()) {
      throw new IllegalStateException("Cannot execute compound command: one or more commands cannot be executed.");
    }

    commands.forEach(command -> {
      command.execute();
      executedCommands.add(command);
    });
  }

  @Override
  public void undo() {
    IntStream.iterate(executedCommands.size() - 1, i -> i >= 0, i -> i - 1).forEachOrdered(i -> executedCommands.get(i).undo());
    executedCommands.clear();
  }
}
