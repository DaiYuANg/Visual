package org.visual.model.language.gui.ide.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.language.gui.ide.service.IProjectManager;
import org.visual.model.language.gui.ide.service.ProjectManager;

@Slf4j
public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IProjectManager.class).to(ProjectManager.class).in(Singleton.class);
	}
}
