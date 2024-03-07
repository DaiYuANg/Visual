package org.visual.component.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import org.kordamp.ikonli.fluentui.FluentUiRegularAL;
import org.kordamp.ikonli.javafx.FontIcon;

public class FluentUiRegularALButton extends Button {
  private final ObjectProperty<FluentUiRegularAL> iconProperty = new SimpleObjectProperty<>();

  public FluentUiRegularAL getIcon() {
    return iconProperty.get();
  }

  public void setIcon(FluentUiRegularAL value) {
    iconProperty.set(value);
  }

  public FluentUiRegularALButton() {
    iconProperty.addListener((_, _, newValue) -> setGraphic(new FontIcon(newValue)));
  }
}
