package org.visual.model.language.gui.ide;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.eventbus.EventBus;
import jakarta.inject.Inject;
import java.awt.*;
import java.util.Set;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.language.gui.ide.contexts.AsyncContext;
import org.visual.model.language.gui.ide.di.DIContainer;
import org.visual.model.language.gui.ide.lifecycle.LifeCycleManagerModule;
import org.visual.model.language.gui.ide.lifecycle.LifecycleManager;

@Slf4j
public class VisualModelIDE extends Application {

	@Inject
	private EventBus eventBus;

	private final TypeLiteral<Set<LifecycleManager>> typeLiteral = new TypeLiteral<>() {
	};

	private Injector lifeCycileManagerInjector;

	private Set<LifecycleManager> lifecycleManagers;

	@Override
	public void init() {
		log.info("Visual Model init");
		Platform.runLater(() -> DIContainer.INSTANCE.getInjector().injectMembers(this));
//		Boolean theme = Boolean
//				.parseBoolean(Toolkit.getDefaultToolkit().getDesktopProperty("win.xpstyle.themeActive").toString());
		val theme = false;
		Application.setUserAgentStylesheet(
				theme ? new PrimerDark().getUserAgentStylesheet() : new PrimerLight().getUserAgentStylesheet());
		this.notifyPreloader(new Preloader.ErrorNotification("/", "", new Throwable()));
	}

	@Override
	public void start(@NotNull Stage stage) {
		stageInitialize(stage);
		stage.setOnShowing(event -> eventBus.publish("GUI.SHOW", event));
		stage.show();
	}

	private void stageInitialize(Stage stage) {
		log.atInfo().log("stage initialize:{}", stage);
		lifeCycileManagerInjector = Guice.createInjector(new LifeCycleManagerModule(stage));
		lifecycleManagers = lifeCycileManagerInjector.getInstance(Key.get(typeLiteral));
		lifecycleManagers.forEach(LifecycleManager::initialize);
	}

	@SneakyThrows
	@Override
	public void stop() {
		lifecycleManagers.forEach(LifecycleManager::stop);
		AsyncContext.INSTANCE.shutdown();
		log.info("visual model stop");
		System.exit(0);
	}
}
