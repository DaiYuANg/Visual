package org.visual.model.language.gui.ide.views.stages;

import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
@NoArgsConstructor(force = true)
public class SettingsViewStage implements ISettingView {

	StringProperty stringProperty = new SimpleStringProperty("String");
	BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
	IntegerProperty integerProperty = new SimpleIntegerProperty(12);
	DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);

	private final Stage stage = new Stage();

	private final EventBus eventBus;

	@Inject
	public SettingsViewStage(@NotNull EventBus eventBus) {
		this.eventBus = eventBus;
		initStage();
		eventBus.consumer("clickSetting", event -> Platform.runLater(this::openSettings));
	}

	private void initStage() {
		stage.centerOnScreen();
	}

	public void openSettings() {
		log.atInfo().log("stage showing?:{}", stage.isShowing());
		if (stage.isShowing()) {
			stage.close();
		} else {
			stage.show();
		}
	}
}
