package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ProjectController implements Initializable {

  @FXML TabPane root;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}
}
