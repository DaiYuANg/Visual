package org.visual.model.modules;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Module
public class AppModule {
    @Provides
    public Test provideSomeDependency() {
        return new Test();
    }
}