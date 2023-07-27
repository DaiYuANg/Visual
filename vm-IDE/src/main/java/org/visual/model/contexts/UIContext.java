package org.visual.model.contexts;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public enum UIContext {
    UICONTEXT;

    @Setter
    @Nullable
    private Stage stage;

    private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    private Optional<Stage> getStage(){
        return Optional.ofNullable(stage);
    }

    public void initializeSize(){
        if (stage != null) {
            stage.setWidth(bounds.getWidth()*0.9);
            stage.setHeight(bounds.getHeight()*0.9);
        }
    }

    private void minimize(){

    }

    private void maximize(){

    }

    private void fullScreen(){

    }
}
