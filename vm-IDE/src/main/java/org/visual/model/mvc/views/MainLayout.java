package org.visual.model.mvc.views;

import javafx.scene.Parent;
import javafx.scene.Scene;
import org.visual.model.utils.FxmlLoaderHelper;

public enum MainLayout {
    MAIN_LAYOUT;

    private final String fxml = "MainLayout";

    private final Parent parent;

    MainLayout() {
        parent = FxmlLoaderHelper.load(fxml);
    }

    public Scene mainLayoutScene() {
        return parent.getScene();
    }
}
