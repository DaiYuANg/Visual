package org.visual.model.mvc.views;

import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Getter;
import org.visual.model.utils.FxmlLoaderHelper;

public enum MainLayout {
	INSTANCE;

	private final String fxml = "MainLayout";

	private final Parent parent = FxmlLoaderHelper.load(fxml);

	@Getter
	private Scene scene = new Scene(parent);

	MainLayout() {
		MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
		// scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
	}
}
