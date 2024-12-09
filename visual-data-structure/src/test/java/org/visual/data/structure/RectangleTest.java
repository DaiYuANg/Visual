package org.visual.data.structure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

  @Test
  void testRectangle() {
    VisualRectangle.builder().id().type().x().y().properties().parent().build();
  }
}