package org.visual.model.app.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Primary;
import jakarta.inject.Singleton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Factory
@Slf4j
public class UIFactory {

    private final Stage rootStage = new Stage();

    @Bean
    @Primary
    Stage rootStage() {
        rootStage.centerOnScreen();
        rootStage.initStyle(StageStyle.TRANSPARENT);
        rootStage.setResizable(true);
        return rootStage;
    }
}
