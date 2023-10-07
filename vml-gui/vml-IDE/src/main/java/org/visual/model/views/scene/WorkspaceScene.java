package org.visual.model.views.scene;

import atlantafx.base.theme.PrimerDark;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import org.visual.model.utils.FxmlLoaderHelper;

public class WorkspaceScene {

    private final String fxml = "Workspace";

    private final Parent parent = FxmlLoaderHelper.load(fxml);

    @Getter
    private final Scene scene = new Scene(parent);

    public WorkspaceScene() {
        scene.setFill(Color.TRANSPARENT);
        scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

    }
}
