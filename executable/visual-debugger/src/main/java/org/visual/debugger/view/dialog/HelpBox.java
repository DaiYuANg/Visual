/*
 * Scenic View,
 * Copyright (C) 2012 Jonathan Giles, Ander Ruiz, Amy Fowler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.visual.debugger.view.dialog;

import java.util.logging.Level;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.api.StageController;
import org.visual.debugger.view.DisplayUtils;
import org.visual.debugger.view.control.ProgressWebView;

public class HelpBox {

  private static final int SCENE_WIDTH = 1024;
  private static final int SCENE_HEIGHT = 768;

  //    static final Image HELP_ICON = DisplayUtils.getUIImage("help.png");

  final Stage stage;

  public HelpBox(final String title, final String url, final double x, final double y) {
    final BorderPane pane = new BorderPane();
    pane.setId(StageController.FX_CONNECTOR_BASE_ID + "HelpBox");
    pane.setPrefWidth(SCENE_WIDTH);
    pane.setPrefHeight(SCENE_HEIGHT);
    val wview = new ProgressWebView();
    wview.setPrefHeight(SCENE_HEIGHT);
    wview.setPrefWidth(SCENE_WIDTH);
    wview.doLoad(url);
    pane.setCenter(wview);
    final Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
    stage = new Stage();
    stage.setTitle(title);
    stage.setScene(scene);
    //        stage.getIcons().add(HELP_ICON);
    stage.setOnCloseRequest(arg0 -> DisplayUtils.showWebView(false));
    stage.show();
  }

  static Level wLevel;
  static Level wpLevel;

  public static @NotNull HelpBox make(
      final String title, final String url, final @NotNull Stage stage) {
    DisplayUtils.showWebView(true);
    return new HelpBox(
        title,
        url,
        stage.getX() + (stage.getWidth() / 2) - ((double) SCENE_WIDTH / 2),
        stage.getY() + (stage.getHeight() / 2) - ((double) SCENE_HEIGHT / 2));
  }
}
