package org.visual.app.lifecycle;

import io.avaje.inject.Lazy;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.component.DialogStage;
import org.visual.app.constant.ViewConstant;
import org.visual.app.repository.SavedStateRepository;
import org.visual.app.util.FXMLHelper;

@Singleton
@Slf4j
@Lazy
@RequiredArgsConstructor
public class CheckWorkspaceLifecycle implements StageLifecycle {

  private final SavedStateRepository savedStateRepository;

  private final FXMLHelper fxmlHelper;

  private final EventBus eventBus;

  @Override
  public void beforeShown(Stage stage) {
    val savedStates = savedStateRepository.queryState();
    if (savedStates.isEmpty()) {
      showGettingStarted();
    }
  }

  private void showGettingStarted() {
    log.atInfo().log("Show init dialog");
    val stage = new DialogStage();
    eventBus.consumer("close-dialog", message -> {
      log.atInfo().log("message:{}", message.body());
      Platform.runLater(stage::close);
    });
    val rootNode = fxmlHelper.loadView(ViewConstant.GETTING_STARTED, StackPane.class);
    val scene = new Scene(rootNode);
    stage.setScene(scene);
    stage.showAndWait();
  }
}
