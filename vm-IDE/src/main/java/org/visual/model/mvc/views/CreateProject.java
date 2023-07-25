package org.visual.model.mvc.views;

import javafx.scene.Parent;
import javafx.stage.Stage;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.utils.FxmlLoaderHelper;

@Slf4j
@ToString
public enum CreateProject {
    CREATE_PROJECT;

    private final String fxml = "CreateProject";

    private final Stage window = new Stage();

    private final Parent createProjectScene;

    CreateProject() {
        createProjectScene = FxmlLoaderHelper.load(fxml);
        window.setScene(createProjectScene.getScene());
    }

    public void show(){
        window.showAndWait();
    }
}
