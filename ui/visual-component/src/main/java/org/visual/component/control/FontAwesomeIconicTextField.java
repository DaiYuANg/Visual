package org.visual.component.control;

import atlantafx.base.controls.CustomTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class FontAwesomeIconicTextField extends CustomTextField {

  private final SimpleObjectProperty<FontAwesomeSolid> _icon = new SimpleObjectProperty<>();

  private final SimpleObjectProperty<IconicPos> _iconPos =
      new SimpleObjectProperty<>(IconicPos.LEFT);

  public FontAwesomeSolid getIcon() {
    return _icon.get();
  }

  public void setIcon(FontAwesomeSolid value) {
    _icon.set(value);
  }

  @SuppressWarnings("unused")
  public IconicPos getIconPos() {
    return _iconPos.get();
  }

  public void setIconPos(IconicPos value) {
    _iconPos.set(value);
  }

  public FontAwesomeIconicTextField() {
    InvalidationListener listener =
        b -> {
          FontAwesomeSolid newIcon = _icon.get();
          IconicPos newIconPos = _iconPos.get();
          setupIcon(newIcon, newIconPos);
        };
    _icon.addListener(listener);
    _iconPos.addListener(listener);
  }

  private void setupIcon(FontAwesomeSolid icon, @NotNull IconicPos iconPos) {
    FontIcon fontIcon = new FontIcon(icon);
    setRight(null);
    setLeft(null);
    switch (iconPos) {
      case LEFT:
        setLeft(fontIcon);
        break;
      case RIGHT:
        setRight(fontIcon);
        break;
      default:
        throw new UnsupportedOperationException();
    }
  }
}
