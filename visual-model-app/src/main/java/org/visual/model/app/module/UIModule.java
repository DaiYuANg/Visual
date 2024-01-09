package org.visual.model.app.module;

import com.google.inject.AbstractModule;
import javafx.stage.Stage;
import org.visual.model.app.provider.RootStageProvider;

public class UIModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Stage.class).toProvider(RootStageProvider.class);
    }
}
