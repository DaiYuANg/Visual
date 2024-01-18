package org.visual.model.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface ConstructorStep {
    int order() default 0;
}
