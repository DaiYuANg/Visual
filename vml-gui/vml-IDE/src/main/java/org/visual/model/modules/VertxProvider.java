package org.visual.model.modules;

import com.google.inject.Provider;
import io.vertx.core.Vertx;
import lombok.val;

public class VertxProvider implements Provider<Vertx> {
    @Override
    public Vertx get() {
        return Vertx.vertx();
    }
}
