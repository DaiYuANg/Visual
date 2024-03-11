package org.visual.component.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import org.kordamp.ikonli.fluentui.FluentUiRegularMZ;
import org.kordamp.ikonli.javafx.FontIcon;

public class FluentUiRegularMZButton extends Button {
  private final ObjectProperty<FluentUiRegularMZ> iconProperty = new SimpleObjectProperty<>();

  public FluentUiRegularMZ getIcon() {
    return iconProperty.get();
  }

  public void setIcon(FluentUiRegularMZ value) {
    iconProperty.set(value);
  }

  public FluentUiRegularMZButton() {
    iconProperty.addListener((a, b, newValue) -> setGraphic(new FontIcon(newValue)));
  }
}
