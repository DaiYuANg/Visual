package org.visual.model.views.scene;

import atlantafx.base.theme.PrimerDark;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.utils.FxmlLoaderUtil;

import java.util.Objects;

public class WorkspaceScene implements SceneView {

    private final String fxml = "Workspace";

    private Parent parent;

    @Getter
    private Scene scene;

    private void initScene() {
        scene.setFill(Color.TRANSPARENT);
        scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
    }

    @Override
    public Scene initializeScene() {
        this.parent = FxmlLoaderUtil.load(fxml);
        this.scene = new Scene(parent);
        return scene;
    }
}
