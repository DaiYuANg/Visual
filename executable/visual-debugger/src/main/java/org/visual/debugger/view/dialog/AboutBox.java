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

import java.util.Properties;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.jetbrains.annotations.NotNull;
import org.visual.debugger.VisualModelDebugger;
import org.visual.debugger.api.StageController;
import org.visual.debugger.utils.PropertiesUtils;
import org.visual.debugger.view.ScenicViewGui;

public class AboutBox {
  private static final int SCENE_WIDTH = 476;
  private static final int SCENE_HEIGHT = 500;
  private static final int LEFT_AND_RIGHT_MARGIN = 30;
  private static final int SPACER_Y = 38;
  private final Stage stage;

  private AboutBox(final String title, final double x, final double y) {
    VBox panel = new VBox();
    panel.setId(StageController.FX_CONNECTOR_BASE_ID + "AboutBox");
    panel.getStyleClass().add("about");

    Button footer = new Button("Close");
    footer.setDefaultButton(true);
    footer.setOnAction(
        new EventHandler<>() {
          @Override
          public void handle(final ActionEvent arg0) {
            stage.close();
          }
        });
    VBox.setMargin(
        footer,
        new Insets(
            (double) SPACER_Y / 2,
            LEFT_AND_RIGHT_MARGIN,
            (double) SPACER_Y / 2,
            LEFT_AND_RIGHT_MARGIN));

    //        ImageView header = new ImageView(DisplayUtils.getUIImage("about-header.png"));
    //        header.setId("AboutHeader");

    //        VBox.setMargin(header, new Insets(42.0D, LEFT_AND_RIGHT_MARGIN, 0.0D,
    // LEFT_AND_RIGHT_MARGIN));

    TextArea textArea = new TextArea();
    textArea.setFocusTraversable(false);
    textArea.setEditable(false);
    //        this.textArea.setId("aboutDialogDetails");
    textArea.setText(getAboutText());
    textArea.setWrapText(true);
    //        this.textArea.setPrefHeight(250.0D);
    VBox.setMargin(
        textArea, new Insets(SPACER_Y, LEFT_AND_RIGHT_MARGIN, 0.0D, LEFT_AND_RIGHT_MARGIN));
    VBox.setVgrow(textArea, Priority.ALWAYS);
    panel.setAlignment(Pos.TOP_CENTER);
    panel.getChildren().addAll(textArea, footer);

    Scene scene = new Scene(panel, SCENE_WIDTH, SCENE_HEIGHT);

    this.stage = new Stage(StageStyle.UTILITY);
    this.stage.setTitle(title);
    this.stage.initModality(Modality.APPLICATION_MODAL);
    this.stage.setScene(scene);
    //        this.stage.getIcons().add(ScenicViewGui.APP_ICON);
    this.stage.setResizable(false);
    this.stage.setX(x);
    this.stage.setY(y);
    this.stage.show();
  }

  public static void make(final String title, final @NotNull Stage stage) {
    new AboutBox(
        title,
        stage.getX() + (stage.getWidth() / 2) - ((double) SCENE_WIDTH / 2),
        stage.getY() + (stage.getHeight() / 2) - (SCENE_HEIGHT / 2));
  }

  private static @NotNull String getAboutText() {
    final Properties properties = PropertiesUtils.getProperties();
    String toolsPath = properties.getProperty(VisualModelDebugger.JDK_PATH_KEY);
    toolsPath = toolsPath == null ? "Included in runtime classpath" : toolsPath;

    return "JavaFX Scenic View "
        + ScenicViewGui.VERSION
        + "\n"
        + "Scenic View developed by Amy Fowler, Ander Ruiz and Jonathan Giles\n"
        + "\n"
        + "JavaFX Build Information:"
        + "\n"
        + "    Java FX "
        + System.getProperty("javafx.runtime.version")
        + "\n"
        + "\n"
        + "Required Libraries:\n"
        + "    tools.jar Home: "
        + toolsPath
        + "\n\n"
        + "Operating System:\n"
        + "    "
        + System.getProperty("os.name")
        + ", "
        + System.getProperty("os.arch")
        + ", "
        + System.getProperty("os.version")
        + "\n\nJava Version:\n"
        + "    "
        + System.getProperty("java.version")
        + ", "
        + System.getProperty("java.vendor")
        + ", "
        + System.getProperty("java.runtime.version");
  }
}
