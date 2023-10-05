package org.visual.model.di.modules;

import com.google.inject.AbstractModule;

import java.io.InputStream;
import java.util.Properties;

import com.google.inject.name.Named;
import com.google.inject.name.Names;
import dev.dirs.BaseDirectories;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.constants.InternalConfigKey;

@Slf4j
public class InternalPropertyModule extends AbstractModule {

    ClassLoader classLoader = InternalPropertyModule.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("application.properties");

    Properties properties = new Properties();

    @SneakyThrows
    public InternalPropertyModule() {
        properties.load(inputStream);
    }

    @Override
    protected void configure() {
        val applicationName = properties.getProperty(InternalConfigKey.APPLICATION_NAME.getValue());
        bind(String.class)
                .annotatedWith(Names.named("ApplicationName"))
                .toInstance(applicationName);

        bind(String.class)
                .annotatedWith(Names.named("ApplicationCache"))
                .toInstance(BaseDirectories.get().cacheDir + applicationName);

        bind(String.class)
                .annotatedWith(Names.named("ApplicationWorkspace"))
                .toInstance(BaseDirectories.get().homeDir + applicationName);
    }
}
