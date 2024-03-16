package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import lombok.extern.slf4j.Slf4j;
import org.visual.component.EditorTab;
import org.visual.context.CurrentContext;
import org.visual.context.ProjectContext;

@Slf4j
@Singleton
public class ProjectController implements Initializable {

  @FXML TabPane root;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ProjectContext.INSTANCE.listen(
        project -> {
          CurrentContext.INSTANCE.setCurrent(project.getUuid());
          log.atTrace().log("Project Name:{}", project);
          root.getTabs().add(new EditorTab());
        });
  }
}
