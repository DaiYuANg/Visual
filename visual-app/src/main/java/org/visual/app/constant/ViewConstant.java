package org.visual.app.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewConstant {
  MAIN_LAYOUT("MainLayout"),

  DATABASE_CONNECT_FORM("dialog/DatabaseConnectionForm"),

  SETTING("Setting"),

  GETTING_STARTED("dialog/GettingStarted"),

  ABOUT("dialog/About");

  private final String viewName;
}
