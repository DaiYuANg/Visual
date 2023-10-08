package org.visual.model.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.visual.model.VisualModelIDE;
import org.visual.model.di.DIContainer;

@UtilityClass
public class FxmlLoaderUtil {

	@SneakyThrows
	public static Parent load(String prefix) {
		FXMLLoader loader = new FXMLLoader(VisualModelIDE.class.getResource(prefix + ".fxml"));
		loader.setControllerFactory(DIContainer.INSTANCE.getInjector()::getInstance);
		return loader.load();
	}
}
