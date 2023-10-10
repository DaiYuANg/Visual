package org.visual.model.views.scene;

import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Getter;
import org.visual.model.utils.FxmlLoaderUtil;

public class CreateProjectScene implements SceneView {
    private final String fxml = "CreateProject";
    private Parent parent;

    @Getter
    private Scene scene;

    public CreateProjectScene() {

    }

    @Override
    public Scene initializeScene() {
        this.parent = FxmlLoaderUtil.load(fxml);
        this.scene = new Scene(parent);
        return scene;
    }
}
