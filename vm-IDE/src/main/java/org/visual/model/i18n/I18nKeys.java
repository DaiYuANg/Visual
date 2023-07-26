package org.visual.model.i18n;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum I18nKeys {
    CONFIRM("confirm"),

    CONFIRM_CLOSE_HEADER("confirm.close.header");

    private final String value;
}
