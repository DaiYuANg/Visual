package org.visual.model.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum EventBuses {
    SET_LOCALE_EVENT("SET_LOCALE_EVENT"),

    STAGE_SWITCH_SCENE("STAGE_SWITCH_SCENE");

    private final String value;
}
