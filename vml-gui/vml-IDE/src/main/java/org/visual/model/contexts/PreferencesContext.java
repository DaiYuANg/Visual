package org.visual.model.contexts;

import java.util.prefs.Preferences;

public enum PreferencesContext {
	PREFERENCES;

	private final String preferenceNode = "org.visual.model.preference";
	private final Preferences preferences = Preferences.userRoot().node(preferenceNode);

	PreferencesContext() {
	}
}
