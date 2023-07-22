package org.visual.model.i18n;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum I18nKeys {
    CONFIRM("confirm"),

    CONFIRM_CLOSE_HEADER("confirm.close.header");

    @Getter
    private final String value;
}
