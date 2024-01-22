package org.visual.model.event.annotation;

import org.visual.model.event.AbstractEvent;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EventListener {
    Class<? extends AbstractEvent> value();
}
