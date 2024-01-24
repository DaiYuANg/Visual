package org.visual.model.component.control.dialog;

import static org.visual.model.component.util.FXUtils.observeWidth;

import java.util.Objects;
import javafx.scene.control.Alert;
import org.visual.model.component.display.ThemeLabel;
import org.visual.model.component.font.FontManager;
import org.visual.model.component.font.FontUsage;
import org.visual.model.component.font.FontUsages;
import org.visual.model.component.manager.internal_i18n.InternalI18n;
import org.visual.model.component.util.FXUtils;

public class SimpleAlert extends ThemeAlertBase {
    private SimpleAlert(String title, String contentText, FontUsage fontUsage) {

        setTitle(title);
        var alertMessage = new ThemeLabel(contentText) {{
            setWrapText(true);
            FontManager.get().setFont(fontUsage, this);
        }};
        observeWidth(getSceneGroup().getNode(), alertMessage, -PADDING_H * 2);

        alertMessagePane.getChildren().add(alertMessage);
    }

    private static String typeToTitle(Alert.AlertType type) {
        return switch (type) {
            case INFORMATION -> InternalI18n.get().alertInfoTitle();
            case WARNING -> InternalI18n.get().alertWarningTitle();
            case ERROR -> InternalI18n.get().alertErrorTitle();
            case null, default -> Objects.requireNonNull(type).name();
        };
    }

    public static void show(Alert.AlertType type, String contentText) {
        show(typeToTitle(type), contentText);
    }

    public static void show(Alert.AlertType type, String contentText, FontUsage fontUsage) {
        show(typeToTitle(type), contentText, fontUsage);
    }

    public static void showAndWait(Alert.AlertType type, String contentText) {
        showAndWait(typeToTitle(type), contentText);
    }

    public static void showAndWait(Alert.AlertType type, String contentText, FontUsage fontUsage) {
        showAndWait(typeToTitle(type), contentText, fontUsage);
    }

    public static void show(String title, String contentText) {
        show(title, contentText, FontUsages.alert);
    }

    public static void show(String title, String contentText, FontUsage fontUsage) {
        FXUtils.runOnFX(() -> new SimpleAlert(title, contentText, fontUsage).show());
    }

    public static void showAndWait(String title, String contentText) {
        showAndWait(title, contentText, FontUsages.alert);
    }

    public static void showAndWait(String title, String contentText, FontUsage fontUsage) {
        new SimpleAlert(title, contentText, fontUsage).showAndWait();
    }
}
