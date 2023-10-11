package org.visual.model.language.gui.ide.constants;

import lombok.Getter;
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
