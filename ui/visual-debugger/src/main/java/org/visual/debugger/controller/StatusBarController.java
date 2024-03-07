package org.visual.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import org.visual.debugger.context.AttachSceneContext;

@Singleton
public class StatusBarController implements Initializable {

  @FXML HBox hBox;

  @FXML Label nodeCount;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    nodeCount
        .textProperty()
        .bindBidirectional(
            AttachSceneContext.INSTANCE.getNodeCounts(),
            new StringConverter<>() {
              @Override
              public String toString(Number object) {
                return object.toString();
              }

              @Override
              public Number fromString(String string) {
                return Integer.valueOf(string);
              }
            });
  }
}
