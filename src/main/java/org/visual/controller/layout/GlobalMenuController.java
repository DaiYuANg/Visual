package org.visual.controller.layout;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class GlobalMenuController implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void openDebugger(ActionEvent actionEvent) {}

  public void enableInspector(ActionEvent actionEvent) {}
}
