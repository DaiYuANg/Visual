package org.visual.model.language.gui.ide.di.providers;

import com.google.gson.Gson;
import jakarta.inject.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GsonProvider implements Provider<Gson> {

	@Override
	public Gson get() {
		return new Gson();
	}
}
