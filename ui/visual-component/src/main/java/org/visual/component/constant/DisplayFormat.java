package org.visual.component.constant;

import java.text.DecimalFormat;
import java.text.Format;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DisplayFormat {
  DOUBLE_FORMAT(new DecimalFormat("0.00"));

  private final Format format;
}
