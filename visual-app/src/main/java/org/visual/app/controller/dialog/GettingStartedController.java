package org.visual.app.controller.dialog;

import dev.dirs.UserDirectories;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.component.NavigationPane;
import org.visual.app.constant.EventBusNaming;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class GettingStartedController implements Initializable {

  @FXML
  private WebView webview;
  @FXML
  private NavigationPane root;

  private final EventBus eventBus;

  private final File userDir = new File(UserDirectories.get().homeDir);

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void handleCreateNewProject(ActionEvent actionEvent) {

  }

  public void handleOpenExistingProject(ActionEvent actionEvent) {
    val window = root.getScene().getWindow();
    val chooser = new DirectoryChooser();
    chooser.setInitialDirectory(userDir);
    val dir = chooser.showDialog(window);
    log.atInfo().log("Choose :{}", dir);
  }

  @SneakyThrows
  public void handleViewDocumentation(ActionEvent actionEvent) {
    root.to("/document");
    webview.getEngine().load("https://github.com/DaiYuANg/Visual");
  }

  public void handleSettings(ActionEvent actionEvent) {

  }

  public void handleCreateNewWorkspace(ActionEvent actionEvent) {

  }

  public void handleCreateNewDiagram(ActionEvent actionEvent) {
  }

  public void handleExit() {
    closeGettingStartWindow();
  }

  private void closeGettingStartWindow() {
    eventBus.send(EventBusNaming.CLOSE_GETTING_START_WINDOW, null);
  }

  public void handleOpenExistingDiagram() {
    val chooser = new FileChooser();
    chooser.setInitialDirectory(userDir);
    val window = root.getScene().getWindow();
    chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("visual file", ".visual"));
    val file = chooser.showOpenDialog(window);
    log.atInfo().log("Choose:{}", file);
  }
}
