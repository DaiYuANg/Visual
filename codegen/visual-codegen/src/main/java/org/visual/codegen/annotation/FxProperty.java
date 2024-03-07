package org.visual.codegen.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface FxProperty {

  boolean lazy() default true;
}
