package org.visual.app.controller.dialog;

import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.document.FindDocument;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class GettingStartedController implements Initializable {

  @FXML
  private WebView webView;
  @FXML
  private StackPane root;

  private final EventBus eventBus;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void handleCreateNewProject(ActionEvent actionEvent) {

  }

  public void handleOpenExistingProject(ActionEvent actionEvent) {
  }

  @SneakyThrows
  public void handleViewDocumentation(ActionEvent actionEvent) {
    val parent = root.getParent();
    log.atInfo().log("Parent:{}", parent);
    root.getChildren().clear();
    val documentPath = FindDocument.getDocumentURL(Locale.CHINA);
    log.atInfo().log("DOcument Path:{}", documentPath);
//    webView.setVisible(true);
//    webView.getEngine().load(resourceInfo.url().toExternalForm());

    // 将 WebView 添加到 StackPane 中
    root.getChildren().add(webView);
  }

  public void handleSettings(ActionEvent actionEvent) {

  }

  public void handleExit(ActionEvent actionEvent) {
    eventBus.publish("close-dialog", "close");
  }
}
