package org.visual.model.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FxmlLoaderHelper {

    @SneakyThrows
    public static Parent load(String prefix){
        return new FXMLLoader(FxmlLoaderHelper.class.getClassLoader().getResource(prefix+".fxml")).load();
    }
}
