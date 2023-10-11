package org.visual.model.language.gui.ide.lifecycle.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.event.fx.handlers.OnCloseRequestHandler;
import org.visual.model.language.gui.ide.lifecycle.LifecycleManager;

@Slf4j
@Singleton
public class StageLifecycleManager implements LifecycleManager {

	@Inject
	private Stage stage;

	private final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

	@Override
	public void initialize() {
		log.atInfo().log("stage initializer executing");
		setOnClose();
		setView();
		listenResizeAndPosition();
	}

	@Override
	public void stop() {
	}

	private void setOnClose() {
		stage.setOnCloseRequest(new OnCloseRequestHandler(stage));
	}

	private void setView() {
		stage.centerOnScreen();
		stage.setTitle(System.getProperty("application.name"));
		stage.setWidth(bounds.getWidth() * 0.5);
		stage.setHeight(bounds.getHeight() * 0.6);
	}

	private void listenResizeAndPosition() {
		stage.widthProperty().addListener((observable, oldValue, newValue) -> {
			log.info(newValue.toString());
		});

		stage.heightProperty().addListener((observable, oldValue, newValue) -> {
			log.info(newValue.toString());
		});
		stage.xProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("X 坐标位置变化：从 " + oldValue + " 到 " + newValue);
		});
		stage.yProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Y 坐标位置变化：从 " + oldValue + " 到 " + newValue);
		});
	}
}
