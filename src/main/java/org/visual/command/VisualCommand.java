package org.visual.command;

import com.google.common.util.concurrent.ServiceManager;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.api.HistoryRepository;
import org.visual.context.DIContext;
import org.visual.view.VisualUI;
import picocli.CommandLine;

@CommandLine.Command(
  name = "Visual",
  mixinStandardHelpOptions = true,
  helpCommand = true,
  subcommands = {OpenCommand.class, CompileCommand.class})
@RequiredArgsConstructor
@Slf4j
public class VisualCommand implements Runnable {
  private final String[] args;

  private final HistoryRepository historyRepository = DIContext.INSTANCE.get(HistoryRepository.class);

  @Override
  public void run() {
    historyRepository.queryHistory();
    Uni.createFrom().item(DIContext.INSTANCE)
      .map((context) -> context.get(ServiceManager.class))
      .emitOn(Infrastructure.getDefaultExecutor())
      .log()
      .invoke(ServiceManager::startAsync)
      .subscribe().with(t -> {
        log.atInfo().log(t.toString());
      });
    Application.launch(VisualUI.class, args);
  }
}
