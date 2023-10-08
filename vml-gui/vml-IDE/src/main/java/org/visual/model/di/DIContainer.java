package org.visual.model.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.visual.model.di.modules.*;

@ToString
@NoArgsConstructor
public enum DIContainer {
    INSTANCE;

    private final RootModule root = new RootModule();

    private final InternalPropertyModule internal = new InternalPropertyModule();

    private final ViewModule view = new ViewModule();

    private final VerticleModule verticle = new VerticleModule();

    private final ProviderModule provider = new ProviderModule();

    @Getter
    final Injector injector = Guice.createInjector(root, provider, internal, view, verticle);
}
