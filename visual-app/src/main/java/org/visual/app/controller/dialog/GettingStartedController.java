package org.visual.app.controller.dialog;

import dev.dirs.UserDirectories;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.component.NavigationPane;
import org.visual.app.context.DiagramContext;
import org.visual.app.util.CreateDiagram;

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

  private final File userDir = new File(UserDirectories.get().homeDir);

  private final DiagramContext diagramContext;

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

  public void handleCreateNewDiagram() {
    Uni.createFrom().item(CreateDiagram.createDefaultDiagram())
      .log()
      .invoke(diagramContext::add)
      .subscribe().with(t -> {
        Platform.runLater(() -> {
          val stage = (Stage) root.getScene().getWindow();
          stage.close();
        });
      });
  }

  public void handleExit() {
    Uni.createFrom().item(root)
      .map(r -> r.getScene().getWindow())
      .map(window -> (Stage) window)
      .subscribe()
      .with(stage -> Platform.runLater(stage::close));
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
