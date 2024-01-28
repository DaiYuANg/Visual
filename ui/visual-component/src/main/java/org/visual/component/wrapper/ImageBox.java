package org.visual.component.wrapper;

import javafx.scene.image.Image;

public interface ImageBox {
  int getWidth();

  int getHeight();

  int getRGB(int x, int y);

  CanvasBox createGraphics();

  Image toFXImage();
}
