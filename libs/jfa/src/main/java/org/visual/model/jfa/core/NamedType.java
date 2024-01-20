/* (C)2024*/
package org.visual.model.jfa.core;

import lombok.Getter;

@Getter
public class NamedType {
    private final Class<?> type;
    private final String name;

    public NamedType(Class<?> type, String name) {
        this.type = type;
        this.name = name;
    }
}
