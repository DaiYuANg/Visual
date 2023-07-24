package org.visual.model.contexts;

import javafx.stage.Stage;

public enum UIContext {
    UICONTEXT(null);

    final Stage stage;

    UIContext(Stage stage) {
        this.stage = stage;
    }
}
