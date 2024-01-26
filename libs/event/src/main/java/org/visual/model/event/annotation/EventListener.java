package org.visual.model.event.annotation;

import java.lang.annotation.*;
import org.visual.model.event.AbstractEvent;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EventListener {
    Class<? extends AbstractEvent> value();
}
