package org.visual.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FXMLView {
  MAIN_LAYOUT("MainLayout"),

  CREATION("creation/Creation"),

  ER_GUIDE("guide/ERGuide"),

  EDITOR("Editor"),

  OBJECT_ORIENTED_GUIDE("guide/ObjectOrientedGuide");

  private final String view;
}
