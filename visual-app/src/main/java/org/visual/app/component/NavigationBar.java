package org.visual.app.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import lombok.Getter;

import static javafx.scene.layout.Priority.ALWAYS;

@Getter
public class NavigationBar extends HBox {

  private final IconButton backButton = new IconButton("mdi2a-arrow-collapse-left");
  private final IconButton forwardButton = new IconButton("mdi2a-arrow-collapse-right");

  private final Region spacer = new Region();

  public NavigationBar() {
    super(20);
    setAlignment(Pos.CENTER);
    HBox.setHgrow(spacer, ALWAYS);
    getChildren().addAll(backButton, spacer, forwardButton);
  }

  public void setOnForward(EventHandler<ActionEvent> event) {
    forwardButton.setOnAction(event);
  }

  public void setOnBack(EventHandler<ActionEvent> event) {
    backButton.setOnAction(event);
  }
}
