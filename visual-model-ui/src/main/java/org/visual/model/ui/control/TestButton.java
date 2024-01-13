package org.visual.model.ui.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fluentui.FluentUiFilledAL;

public class TestButton extends Application {
    @Override
    public void start(@NotNull Stage stage) throws Exception {

        val a = new AnchorPane();
        val s = new Scene(a, 500, 500);
        val ib = new SwitchButton();
        a.getChildren().add(ib);
        stage.setScene(s);
        stage.show();
    }
}
