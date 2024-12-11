package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.app.controller.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class TabController extends BaseController {

  @FXML
  private TabPane root;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }
}
