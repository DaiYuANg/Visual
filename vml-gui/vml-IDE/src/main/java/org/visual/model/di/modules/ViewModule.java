package org.visual.model.di.modules;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.AbstractModule;
import org.visual.model.di.providers.FxTrayIconProvider;

public class ViewModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(FXTrayIcon.class).toProvider(FxTrayIconProvider.class);
	}
}
