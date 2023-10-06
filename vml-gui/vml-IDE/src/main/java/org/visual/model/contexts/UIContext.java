package org.visual.model.contexts;

import java.util.Objects;
import java.util.Optional;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.visual.model.views.scene.WorkspaceScene;

public enum UIContext {
	UICONTEXT;

	@Setter
	@Nullable private Stage stage;

	private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

	private Optional<Stage> getStage() {
		return Optional.ofNullable(stage);
	}

	public void initializeSize() {
		Objects.requireNonNull(stage).initStyle(StageStyle.TRANSPARENT);
		stage.setResizable(true);
		stage.setScene(WorkspaceScene.INSTANCE.getScene());
		if (stage != null) {
			stage.setWidth(bounds.getWidth() * 0.9);
			stage.setHeight(bounds.getHeight() * 0.9);
		}
	}
}
