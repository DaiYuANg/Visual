package org.visual.model.views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.model.utils.FxmlLoaderHelper;

public enum MainLayout {
	INSTANCE;

	private final String fxml = "MainLayout";

	private final Parent parent = FxmlLoaderHelper.load(fxml);

	@Getter
	private final Scene scene = new Scene(parent);

	MainLayout() {
		scene.setFill(Color.TRANSPARENT);
		// scene.getch
		// MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
		// scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
	}
}
