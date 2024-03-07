package org.visual.component.control.dialog;

import static org.visual.component.util.FXUtils.observeWidth;

import java.util.Objects;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;
import org.visual.component.util.FxUtil;

public class SimpleAlert extends ThemeAlertBase {
  private SimpleAlert(String title, String contentText) {

    setTitle(title);
    var alertMessage =
        new Label(contentText) {
          {
            setWrapText(true);
          }
        };
    observeWidth(getSceneGroup().getNode(), alertMessage, -PADDING_H * 2);

    alertMessagePane.getChildren().add(alertMessage);
  }

  private static String typeToTitle(Alert.@NotNull AlertType type) {
    return switch (type) {
        //      case INFORMATION -> InternalI18n.get().alertInfoTitle();
        //      case WARNING -> InternalI18n.get().alertWarningTitle();
        //      case ERROR -> InternalI18n.get().alertErrorTitle();
      default -> Objects.requireNonNull(type).name();
    };
  }

  public static void show(Alert.AlertType type, String contentText) {
    show(typeToTitle(type), contentText);
  }

  public static void show(String title, String contentText) {
    FxUtil.runOnFx(() -> new SimpleAlert(title, contentText).show());
  }
}
