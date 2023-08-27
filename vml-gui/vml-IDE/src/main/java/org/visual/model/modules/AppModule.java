package org.visual.model.modules;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Singleton
public class AppModule {
    @Provides
    public Test provideSomeDependency() {
        return new Test();
    }
}