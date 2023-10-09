package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.views.SettingsView;
import org.visual.model.views.scene.CreateProjectScene;
import org.visual.model.views.scene.WorkspaceScene;

@Slf4j
public class ViewModule extends AbstractModule {


    public ViewModule() {
        log.atInfo().log("bind view modules");
    }

    @Override
    protected void configure() {
        bind(WorkspaceScene.class).toProvider(WorkspaceScene::new).asEagerSingleton();
        bind(CreateProjectScene.class).toProvider(CreateProjectScene::new).asEagerSingleton();
        bind(SettingsView.class).toProvider(SettingsView::new).asEagerSingleton();
    }
}
