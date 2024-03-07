package org.visual.component.control;

import javafx.beans.NamedArg;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class FontAwesomeSolidButton extends Button {
  private final ObjectProperty<FontAwesomeSolid> iconProperty = new SimpleObjectProperty<>();

  public FontAwesomeSolidButton(@NamedArg("initIcon") FontAwesomeSolid initIcon) {
    iconProperty.addListener(
        (observable, oldValue, newValue) -> setGraphic(new FontIcon(newValue)));

    if (initIcon != null) {
      setIcon(initIcon);
    }
  }

  public FontAwesomeSolid getIcon() {
    return iconProperty.get();
  }

  public void setIcon(FontAwesomeSolid value) {
    iconProperty.set(value);
  }
}
