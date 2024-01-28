package org.visual.model.component.font;

import org.junit.jupiter.api.Test;
import org.visual.component.font.FontManager;

class FontManagerTest {

  @Test
  void getFont() {
    System.err.println(FontManager.get().getFont());
  }
}
