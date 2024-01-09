package org.visual.model.app.provider;

import jakarta.inject.Provider;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.val;

public class RootStageProvider implements Provider<Stage> {
    @Override
    public Stage get() {
        val stage = new javafx.stage.Stage();
        stage.centerOnScreen();
        stage.initStyle(StageStyle.TRANSPARENT);
        return stage;
    }
}
