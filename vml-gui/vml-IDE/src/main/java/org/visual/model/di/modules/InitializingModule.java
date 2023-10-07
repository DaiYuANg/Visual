package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.initializing.IStageInitializer;
import org.visual.model.initializing.StageInitializer;

@Slf4j
public class InitializingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IStageInitializer.class).to(StageInitializer.class);
    }
}
