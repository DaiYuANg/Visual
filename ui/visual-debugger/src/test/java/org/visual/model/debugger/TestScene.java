package org.visual.model.debugger;

import static org.visual.debugger.controller.ConnectorUtils.getBranchCount;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class TestScene {

  private final Pane pane = new Pane();

  private final VBox vBox = new VBox();

  private final HBox hBox = new HBox();

  @Start
  void start(@NotNull Stage stage) {
    SplitPane splitPane = new SplitPane();
    Label label = new Label("test");
    vBox.getChildren().add(label);
    splitPane.getItems().addAll(vBox, hBox);
    pane.getChildren().add(splitPane);
    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.show();
  }

  @Test
  void testNodeCount() {
    val bc = getBranchCount(pane);
    System.err.println(bc);
    //        System.err.println(pane.getChildren());
  }
}
