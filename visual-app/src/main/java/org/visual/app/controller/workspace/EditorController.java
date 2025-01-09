package org.visual.app.controller.workspace;

import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.visual.editor.core.VisualEditor;
import org.visual.editor.view.EditorContainer;
import org.visual.editor.view.model.EditorProperties;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class EditorController implements Initializable {

  @FXML
  private EditorContainer container;

  private final EditorProperties properties = new EditorProperties();

  private final VisualEditor visualEditor = new VisualEditor(properties);

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
//    val model = VModel.builder()
//      .version("1.0")
//      .type("graph")
//      .contentWidth(1000.0)
//      .contentHeight(1000.0)
//      .build();
//    visualEditor.setModel(model);
//    container.setVisualEditor(visualEditor);
  }
}
