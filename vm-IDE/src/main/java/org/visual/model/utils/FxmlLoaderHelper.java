package org.visual.model.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.visual.model.VisualModelIDE;

@UtilityClass
public class FxmlLoaderHelper {

	@SneakyThrows
	public static Parent load(String prefix) {
		return new FXMLLoader(VisualModelIDE.class.getResource(prefix + ".fxml")).load();
	}
}
