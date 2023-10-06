package org.visual.model.di.modules;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.AbstractModule;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.di.providers.FxTrayIconProvider;

@Slf4j
public class ViewModule extends AbstractModule {

    public ViewModule() {
        log.atInfo().log("bind view modules");
    }

    @Override
    protected void configure() {
        bind(FXTrayIcon.class).toProvider(FxTrayIconProvider.class);
    }
}
