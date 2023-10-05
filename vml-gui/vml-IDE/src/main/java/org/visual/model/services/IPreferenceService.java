package org.visual.model.services;

import org.jetbrains.annotations.NotNull;

public interface IPreferenceService {
	String getWorkspace();

	void putString(String key, @NotNull String value);
}
