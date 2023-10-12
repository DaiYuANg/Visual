package org.visual.model.language.gui.ide.lifecycle.managers;

import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import io.vertx.core.*;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.model.language.gui.ide.contexts.AsyncContext;
import org.visual.model.language.gui.ide.di.DIContainer;
import org.visual.model.language.gui.ide.lifecycle.LifecycleManager;

@Slf4j
public class VertxLifecycleManager implements LifecycleManager {

	private final Map<String, AbstractVerticle> verticles;

	private final Vertx vertx;

	private final TypeLiteral<Map<String, AbstractVerticle>> type = new TypeLiteral<>() {
	};

	{
		this.verticles = DIContainer.INSTANCE.getInjector().getInstance(Key.get(type));
		this.vertx = DIContainer.INSTANCE.getInjector().getInstance(Vertx.class);
	}

	@Override
	public void initialize() {
		log.atInfo().log("vertx initializer executing");
		val dp = new DeploymentOptions().setHa(true).setWorker(true);
		AsyncContext.INSTANCE.run(() -> {
			verticles.values().stream().map(verticle -> vertx.deployVerticle(verticle, dp)).forEach(d -> {
				d.onFailure(event -> log.error(event.getMessage(), event.fillInStackTrace()));
				d.onComplete(event -> log.info(event.result()));
			});
		});
	}

	@Override
	public void stop() {
		log.info("vertx lifecycle stop");
		vertx.deploymentIDs().forEach(vertx::undeploy);
	}
}
