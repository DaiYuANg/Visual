package org.visual.model.language.gui.ide.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectConstant {
	PersistenceSuffix("ser"),

	PersistencePrefix("project");

	private final String value;
}
