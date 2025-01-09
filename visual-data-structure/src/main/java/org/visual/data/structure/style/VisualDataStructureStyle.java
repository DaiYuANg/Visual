package org.visual.data.structure.style;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
  typeImmutable = "V*",
  visibility = Value.Style.ImplementationVisibility.PUBLIC,
  strictBuilder = true,
  jdk9Collections = true
)
@JsonSerialize
public @interface VisualDataStructureStyle {
}
