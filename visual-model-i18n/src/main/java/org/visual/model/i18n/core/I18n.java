package org.visual.model.i18n.core;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;


public class I18n {
    private final ResourceBundle resourceBundle;

    public I18n(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle(I18nHelper.baseName, locale);
    }

    public String get(@NotNull I18nKeys keys) {
        if (resourceBundle.containsKey(keys.getValue())) {
            return resourceBundle.getString(keys.getValue());
        }
        throw new NoSuchElementException();
    }
}