package org.visual.model.ui.layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class TestFlexHBox extends Application {
    @Override
    public void start(@NotNull Stage stage) throws Exception {
        val hb = new FlexHBox();
        val bs = Stream.of("test", "test1", "test2").map(s -> new Button(s)).toList();
        hb.getChildren().addAll(bs);
        val s = new Scene(hb, 200, 200);
        stage.setScene(s);
        stage.show();
    }
}
