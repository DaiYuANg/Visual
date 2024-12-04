package org.visual.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ViewConstant {
  MAIN_LAYOUT("MainLayout"),

  DATABASE_CONNECT_FORM("DatabaseConnectionForm"),

  ABOUT("About");

  private final String viewName;
}
