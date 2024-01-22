package org.visual.model.event;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@ToString
@Accessors(chain = true)
@Getter
public class ListenerArgument<E extends AbstractEvent> {

    private final Class<E> event;

    private final EventListener<E> listener;
}
