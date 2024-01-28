package org.visual.event.annotation;

import java.lang.annotation.*;
import org.visual.event.AbstractEvent;

@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EventListener {
  Class<? extends AbstractEvent> value();
}
