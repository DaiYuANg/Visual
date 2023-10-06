package org.visual.model.views;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.utils.FxmlLoaderHelper;

@Slf4j
@Singleton
public class CreateProject {

	private final String fxml = "CreateProject";

	private final Stage window = new Stage(StageStyle.DECORATED);

	private final Parent createProjectScene;

	@Inject
	CreateProject() {
		createProjectScene = FxmlLoaderHelper.load(fxml);
		window.setScene(createProjectScene.getScene());
	}

	public void show() {
		window.showAndWait();
	}
}
