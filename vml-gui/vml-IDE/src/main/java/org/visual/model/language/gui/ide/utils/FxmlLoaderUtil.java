package org.visual.model.language.gui.ide.utils;

import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.visual.model.language.gui.ide.VisualModelIDE;
import org.visual.model.language.gui.ide.di.DIContainer;

@UtilityClass
public class FxmlLoaderUtil {

	@SneakyThrows
	public static Parent load(String prefix) {
		FXMLLoader loader = new FXMLLoader(VisualModelIDE.class.getResource(prefix + ".fxml"));
		loader.setControllerFactory(DIContainer.INSTANCE.getInjector()::getInstance);
		loader.setCharset(StandardCharsets.UTF_8);
		return loader.load();
	}
}
