package org.visual.model.contexts;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public enum I18nContext {
	I18N;

	private final ResourceBundle i18nResource;

	I18nContext() {
		i18nResource = ResourceBundle.getBundle("bundle", Locale.getDefault());
	}

	public @NotNull String getI18nResource(String key) {
		return i18nResource.getString(key);
	}
}
