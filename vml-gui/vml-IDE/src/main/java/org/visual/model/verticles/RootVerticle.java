package org.visual.model.verticles;

import com.google.inject.Singleton;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class RootVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) {
        log.atInfo().log("root verticle start");
    }
}
