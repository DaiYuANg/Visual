package org.visual.model.contexts;

import lombok.Setter;

import java.util.ResourceBundle;

public enum I18nContext {
    I18N;

    @Setter
    private ResourceBundle i18nResource;
}
