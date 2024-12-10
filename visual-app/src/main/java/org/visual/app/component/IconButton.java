package org.visual.app.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.kordamp.ikonli.javafx.FontIcon;

@Getter
@Slf4j
@SuppressWarnings("unused")
public class IconButton extends Button {

  private final StringProperty icon = new SimpleStringProperty();

  @FXML
  public String getIcon() {
    return icon.get();
  }

  @FXML
  public void setIcon(String icon) {
    this.icon.set(icon);
    setGraphic(new FontIcon(icon));
  }
}
