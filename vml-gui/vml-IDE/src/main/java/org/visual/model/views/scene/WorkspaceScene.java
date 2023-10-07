package org.visual.model.views.scene;

import atlantafx.base.theme.PrimerDark;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.SneakyThrows;
import org.visual.model.di.DIContainer;
import org.visual.model.initializing.Initializer;
import org.visual.model.utils.FxmlLoaderHelper;

import java.io.IOException;

public class WorkspaceScene implements Initializer {

    private final String fxml = "Workspace";

    private Parent parent;

    @Getter
    private Scene scene;

    @Override
    public void initialize() {
        this.parent = FxmlLoaderHelper.load(fxml);
        this.scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        scene.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
    }
}
