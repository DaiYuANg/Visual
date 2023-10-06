package org.visual.model.views.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.model.utils.FxmlLoaderHelper;

public enum WorkspaceScene {
	INSTANCE;

	private final String fxml = "MainLayout";

	private final Parent parent = FxmlLoaderHelper.load(fxml);

	@Getter
	private final Scene scene = new Scene(parent);

	WorkspaceScene() {
		scene.setFill(Color.TRANSPARENT);
		// scene.getch
		// MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
		// scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
	}
}
