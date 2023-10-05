package org.visual.model.services.impl;

import com.google.inject.Singleton;
import java.nio.charset.StandardCharsets;
import java.util.prefs.Preferences;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.services.IPreferenceService;

@Slf4j
@Singleton
public class PreferenceService implements IPreferenceService {
	private final Preferences preferences = Preferences.userRoot();

	public PreferenceService() {
		log.atInfo().log(preferences.absolutePath());
	}

	@Override
	public String getWorkspace() {
		return preferences.get("workspace", System.getProperty("user.home"));
	}

	@Override
	public void putString(String key, @NotNull String value) {
		preferences.putByteArray(key, value.getBytes(StandardCharsets.UTF_8));
	}
}
