/* (C)2024*/
package org.visual.model.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Primary;
import java.nio.file.Path;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.component.widget.Tray;

@Factory
@Slf4j
public class UIFactory {

    private final Stage rootStage = new Stage();

    @Bean
    @Primary
    Stage rootStage() {
        rootStage.centerOnScreen();
//        rootStage.initStyle(StageStyle.TRANSPARENT);
        rootStage.setResizable(true);
        return rootStage;
    }

    @Bean
    Tray tray(Stage rootStage) {
        return new Tray(Path.of(""), rootStage);
    }
}
