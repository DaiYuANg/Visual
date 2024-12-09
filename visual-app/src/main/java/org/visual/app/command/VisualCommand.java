package org.visual.app.command;

import com.google.common.util.concurrent.ServiceManager;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.mutiny.core.net.NetClient;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.context.DIContext;
import org.visual.app.view.VisualUI;
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

  @Override
  public void run() {
    Uni.createFrom().item(DIContext.INSTANCE)
      .map((context) -> context.get(ServiceManager.class))
      .emitOn(Infrastructure.getDefaultExecutor())
      .log()
      .invoke(ServiceManager::startAsync)
      .subscribe().with(t -> {
        log.atInfo().log(t.toString());
      });
    DIContext.INSTANCE.get(NetClient.class)
      .connect(19090, "localhost")
      .subscribe().with(t -> {
        t.handler(buffer -> {
          log.atInfo().log(buffer.toString());
        });
      })
    ;
    Application.launch(VisualUI.class, args);
  }
}
