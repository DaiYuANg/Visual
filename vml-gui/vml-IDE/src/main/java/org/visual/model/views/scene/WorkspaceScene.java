package org.visual.model.views.scene;

import atlantafx.base.theme.PrimerDark;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.utils.FxmlLoaderUtil;

public class WorkspaceScene implements SceneView {

    private final String fxml = "Workspace";

    private Parent parent;

    @Getter
    private Scene scene;

//    @Override
    public void initialize() {
        this.parent = FxmlLoaderUtil.load(fxml);
        this.scene = new Scene(parent);
    }

//    @Override
    public void stop() {

    }

    private void initScene (){
        scene.setFill(Color.TRANSPARENT);
        scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
    }
}
