package org.visual.model.lifecycle;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.di.providers.FxTrayIconProvider;
import org.visual.model.lifecycle.LifecycleManager;
import org.visual.model.lifecycle.managers.StageLifecycleManager;
import org.visual.model.lifecycle.managers.VertxLifecycleManager;

@Slf4j
public class LifeCycileManagerModule extends AbstractModule {
    private final Stage stage;

    public LifeCycileManagerModule(Stage stage) {
        log.atInfo().log("bind view modules");
        this.stage = stage;
    }

    @Override
    protected void configure() {
        bind(Stage.class).toInstance(stage);
        val initializerMultibinder = Multibinder.newSetBinder(binder(), LifecycleManager.class);
        initializerMultibinder.addBinding().to(StageLifecycleManager.class).in(Singleton.class);
        initializerMultibinder.addBinding().to(VertxLifecycleManager.class).in(Singleton.class);
        bind(FXTrayIcon.class).toProvider(FxTrayIconProvider.class);
    }
}
