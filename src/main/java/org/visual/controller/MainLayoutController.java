/* (C)2024*/
package org.visual.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.visual.component.GuideDialog;
import org.visual.local.store.api.ProjectRepository;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MainLayoutController implements Initializable {
  private final ProjectRepository projectRepository;

  private final GuideDialog guideDialog;

  @FXML VBox root;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val isHasHistory = projectRepository.findLatestEdited();
    if (isHasHistory.isEmpty()) {
      guideDialog.showAndWait();
    }
  }
}
