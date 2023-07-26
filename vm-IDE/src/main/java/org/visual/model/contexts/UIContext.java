package org.visual.model.contexts;

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public enum UIContext {
    UICONTEXT;

    @Setter
    @Nullable
    private Stage stage;

    private Optional<Stage> getStage(){
        return Optional.ofNullable(stage);
    }
}
