package org.visual.model.database.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Factory
@Slf4j
public class UIFactory {

    @Bean
    Stage rootStage() {
        return new Stage();
    }
}
