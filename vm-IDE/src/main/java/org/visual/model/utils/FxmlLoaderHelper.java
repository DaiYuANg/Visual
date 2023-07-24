package org.visual.model.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.visual.model.VisualModelApplication;

@UtilityClass
public class FxmlLoaderHelper {

    @SneakyThrows
    public static Parent load(String prefix) {
        return new FXMLLoader(VisualModelApplication.class.getResource(prefix + ".fxml")).load();
    }
}
