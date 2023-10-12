package org.visual.model.language.gui.ide.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.grapher.graphviz.GraphvizModule;
import io.vertx.core.AbstractVerticle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.val;
import org.visual.model.language.gui.ide.di.modules.*;

import java.util.Map;

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
    final Injector injector = Guice.createInjector(service, provider, internal, view, verticle, new GraphvizModule());

    public <T> T getMultiBinding(String key) {
        val type = new TypeLiteral<Map<String, T>>() {
        };
        val instances = injector.getInstance(Key.get(type));
        return instances.get(key);
    }
}
