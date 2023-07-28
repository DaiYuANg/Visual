package org.visual.model.mvc.controllers;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import java.net.URL;
import java.util.ResourceBundle;
import org.visual.model.mvc.base.Controller;

public class TextEditorController implements Controller {
	private GameApplication fxglGame;

	@Override
	public void initialize() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fxglGame = new GameApplication() {
			@Override
			protected void initSettings(GameSettings settings) {
			}
		}; // Replace with your own FXGL game class, e.g., MyFXGLGame
			// fxglGame.;

		// Get the FXGL scene
		// GameScene gameScene = fxglGame.getGameScene();

		// Set the size of the custom control to match the FXGL scene
		// setPrefSize(gameScene.getWidth(), gameScene.getHeight());
		//
		// Add the FXGL scene's root node to the custom control
		// getChildren().add(gameScene.getRoot());
	}
}
