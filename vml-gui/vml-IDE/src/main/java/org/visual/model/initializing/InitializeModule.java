package org.visual.model.initializing;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.providers.FxTrayIconProvider;
import org.visual.model.initializing.Initializer;
import org.visual.model.initializing.StageInitializer;

@Slf4j
public class InitializeModule extends AbstractModule {
    private final Stage stage;

    public InitializeModule(Stage stage) {
        log.atInfo().log("bind view modules");
        this.stage = stage;
    }

    @Override
    protected void configure() {
        bind(Stage.class).toInstance(stage);
        val initializerMultibinder = Multibinder.newSetBinder(binder(), Initializer.class);
        initializerMultibinder.addBinding().to(StageInitializer.class).in(Singleton.class);
        initializerMultibinder.addBinding().to(VertxInitializer.class).in(Singleton.class);
        bind(FXTrayIcon.class).toProvider(FxTrayIconProvider.class);
    }
}
