package org.visual.app.lifecycle;

import io.avaje.inject.Lazy;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.component.DialogStage;
import org.visual.app.component.NavigationPane;
import org.visual.app.repository.SavedStateRepository;
import org.visual.app.util.FXMLHelper;

import static org.visual.app.constant.EventBusNaming.CLOSE_GETTING_START_WINDOW;
import static org.visual.app.constant.ViewConstant.GETTING_STARTED;

@Singleton
@Slf4j
@Lazy
@RequiredArgsConstructor
public class CheckWorkspaceLifecycle implements StageLifecycle {

  private final SavedStateRepository savedStateRepository;

  private final FXMLHelper fxmlHelper;

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
    val rootNode = fxmlHelper.loadView(GETTING_STARTED, NavigationPane.class);
    val scene = new Scene(rootNode);
    stage.setScene(scene);
    stage.showAndWait();
  }
}
