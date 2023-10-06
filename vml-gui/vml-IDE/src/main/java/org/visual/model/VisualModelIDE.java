package org.visual.model;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.contexts.TasksContext;
import org.visual.model.di.DIContainer;

@Slf4j
public class VisualModelIDE extends Application {

	@Override
	public void init() {
		Font.font("");
		Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
		this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
	}

	@Override
	public void start(@NotNull Stage stage) {
		DIContainer.INSTANCE.getInjector().injectMembers(stage);
		stage.show();
	}

	public static void main(String[] args) {
	}

	@SneakyThrows
	@Override
	public void stop() {
		TasksContext.INSTANCE.shutdown();
		log.info("visual model stop");
	}
}
