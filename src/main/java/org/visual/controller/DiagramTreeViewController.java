package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.DiagramTreeView;
import org.visual.context.ProjectContext;

@Singleton
@Slf4j
public class DiagramTreeViewController implements Initializable {

  @FXML DiagramTreeView tree;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ProjectContext.INSTANCE.listen(
        project -> {
          log.atTrace().log("Project Name:{}", project);
          tree.getRoot().setValue(project.getName());
        });
  }
}
