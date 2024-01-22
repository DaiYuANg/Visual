package org.visual.model.event;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class AbstractEvent {
    private final Object source;

    private final long when;
}