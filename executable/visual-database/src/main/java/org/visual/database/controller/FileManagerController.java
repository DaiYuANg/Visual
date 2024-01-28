package org.visual.database.controller;

import jakarta.inject.Singleton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.control.FileTreeView;

@Singleton
@Slf4j
public class FileManagerController implements Initializable {

  @FXML FileTreeView fileTreeView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fileTreeView.setFileRoot(new File("."));
  }
}
