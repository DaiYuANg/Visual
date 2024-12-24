package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.controller.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class ComponentAreaController extends BaseController {

  @FXML
  private VBox areaRoot;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

//  TODO copy area root
  public void dockWindow() {
    log.info("Dock Window");
    val dockStage = new Stage();
    val dockScene = new Scene(areaRoot);
    dockStage.setScene(dockScene);
    dockStage.setWidth(300);
    dockStage.setHeight(600);
    dockStage.show();
  }
}
