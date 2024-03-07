package org.visual.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FXMLView {
  MAIN_LAYOUT("MainLayout");

  private final String view;
}
