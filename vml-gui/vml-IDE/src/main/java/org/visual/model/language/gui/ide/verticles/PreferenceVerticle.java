package org.visual.model.language.gui.ide.verticles;

import com.google.inject.Singleton;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import java.util.prefs.Preferences;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class PreferenceVerticle extends AbstractVerticle {

	private final Preferences preferences = Preferences.userRoot();

	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
	}

	@Override
	public void start() throws Exception {
	}
}
