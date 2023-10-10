package org.visual.model.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.grapher.graphviz.GraphvizModule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.visual.model.di.modules.*;

@ToString
@NoArgsConstructor
public enum DIContainer {
    INSTANCE;

    private final InternalPropertyModule internal = new InternalPropertyModule();

    private final ViewModule view = new ViewModule();

    private final VerticleModule verticle = new VerticleModule();

    private final ProviderModule provider = new ProviderModule();

    private final ServiceModule service = new ServiceModule();

    @Getter
    final Injector injector = Guice.createInjector(service,provider, internal, view, verticle,new GraphvizModule());
}
