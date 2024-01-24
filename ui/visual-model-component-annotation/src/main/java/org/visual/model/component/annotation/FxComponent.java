package org.visual.model.component.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FxComponent {
    String value() default "";

    String description() default "";
}
