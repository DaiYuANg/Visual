package org.visual.component.control;

import atlantafx.base.controls.CustomTextField;
import java.util.Optional;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.codegen.annotation.FxProperty;

public class FontAwesomeIconicTextField extends CustomTextField {

  @FxProperty
  private final SimpleObjectProperty<FontAwesomeSolid> _icon = new SimpleObjectProperty<>();

  public FontAwesomeSolid getIcon() {
    return _icon.get();
  }

  public void setIcon(FontAwesomeSolid value) {
    _icon.set(value);
  }

  private final SimpleObjectProperty<IconicPos> _iconPos =
      new SimpleObjectProperty<>(IconicPos.LEFT);

  @SuppressWarnings("unused")
  public IconicPos getIconPos() {
    return _iconPos.get();
  }

  @SuppressWarnings("unused")
  public void setIconPos(IconicPos value) {
    _iconPos.set(value);
  }

  public FontAwesomeIconicTextField() {
    InvalidationListener listener =
        b -> {
          val newIcon = _icon.get();
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

  public EventHandler<? super MouseEvent> getOnEventClick(
      EventHandler<? super MouseEvent> handler) {
    val item = Optional.ofNullable(getLeft()).orElse(getRight());
    item.setOnMouseClicked(handler);
    return handler;
  }
}
