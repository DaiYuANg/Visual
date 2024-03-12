package org.visual.debugger.inspector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CSSStyleClass {
  COMPONENT_DETAILS("component-details"),
  FIELD_COMPONENT("field-component"),
  CLASS_COMPONENT("class-component"),
  STYLES_COMPONENT("styles-component");

  private final String cssClassName;
}
