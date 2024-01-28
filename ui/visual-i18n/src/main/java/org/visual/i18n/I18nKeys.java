package org.visual.i18n;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum I18nKeys {
  CLICK("click"),

  OPEN("open"),

  SCENE("scene");

  private final String value;
}
