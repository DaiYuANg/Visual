package org.visual.database.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CreateConnectionController implements Initializable {

  @FXML
  BorderPane vBox;

  @FXML
  SplitPane splitPane;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    splitPane.prefHeightProperty().bindBidirectional(vBox.prefHeightProperty());
  }
}
