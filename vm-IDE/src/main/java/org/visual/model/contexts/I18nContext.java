package org.visual.model.contexts;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.ResourceBundle;

public enum I18nContext {
    I18N;

    @Getter
    private final ResourceBundle i18nResource;

    I18nContext() {
        i18nResource = ResourceBundle.getBundle("bundle", Locale.getDefault());
    }

    public @NotNull String getI18nResource(String key) {
        return i18nResource.getString(key);
    }
}
