package org.visual.model.di.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dev.dirs.BaseDirectories;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.constants.InternalConfigKey;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class InternalPropertyModule extends AbstractModule {

    ClassLoader classLoader = InternalPropertyModule.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("application.properties");

    Properties properties = new Properties();

    @SneakyThrows
    public InternalPropertyModule() {
        log.atInfo().log("load internal property config");
        properties.load(inputStream);
    }

    @Override
    protected void configure() {
        bindAboutApplication();
        bindAboutWindow();
    }

    private void bindAboutWindow() {

    }

    private void bindAboutApplication() {
        val applicationName = properties.getProperty(InternalConfigKey.APPLICATION_NAME.getValue());
        bind(String.class)
                .annotatedWith(Names.named("ApplicationName"))
                .toInstance(applicationName);

        bind(String.class)
                .annotatedWith(Names.named("ApplicationCache"))
                .toInstance(BaseDirectories.get().cacheDir + applicationName);

        bind(String.class)
                .annotatedWith(Names.named("ApplicationWorkspace"))
                .toInstance(BaseDirectories.get().homeDir + File.separator + applicationName);

        bind(String.class)
                .annotatedWith(Names.named("ApplicationData"))
                .toInstance(BaseDirectories.get().dataDir + File.separator + applicationName);
    }
}
