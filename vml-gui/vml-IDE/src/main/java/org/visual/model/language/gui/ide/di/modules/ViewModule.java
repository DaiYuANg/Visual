package org.visual.model.language.gui.ide.di.modules;

import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.views.scene.CreateProjectScene;
import org.visual.model.language.gui.ide.views.scene.WorkspaceScene;
import org.visual.model.language.gui.ide.views.stages.ISettingView;
import org.visual.model.language.gui.ide.views.stages.SettingsViewStage;

@Slf4j
public class ViewModule extends AbstractModule {

	public ViewModule() {
		log.atInfo().log("bind view modules");
	}

	@Override
	protected void configure() {
		bind(WorkspaceScene.class).toProvider(WorkspaceScene::new).asEagerSingleton();
		bind(CreateProjectScene.class).toProvider(CreateProjectScene::new).asEagerSingleton();
		bind(ISettingView.class).to(SettingsViewStage.class).asEagerSingleton();
	}
}
