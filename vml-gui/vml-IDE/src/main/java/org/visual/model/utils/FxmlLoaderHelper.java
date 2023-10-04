package org.visual.model.utils;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.visual.model.VisualModelIDE;
import org.visual.model.modules.RootModule;

@UtilityClass
public class FxmlLoaderHelper {
	final Injector injector = Guice.createInjector(new RootModule());
	@SneakyThrows
	public static Parent load(String prefix) {
		FXMLLoader loader = new FXMLLoader(VisualModelIDE.class.getResource(prefix + ".fxml"));
		loader.setControllerFactory(injector::getInstance);
		return loader.load();
	}
}
