package org.visual.debugger.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PreferencesKey {

    SPLIT_DIVIDER("split.divider");

    private final String value;
}
