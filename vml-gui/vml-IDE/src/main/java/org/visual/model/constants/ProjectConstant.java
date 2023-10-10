package org.visual.model.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectConstant {
    PersistenceSuffix("ser"),

    PersistencePrefix("project");

    private final String value;
}
