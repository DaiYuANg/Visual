package org.visual.component.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import javafx.scene.image.WritableImage;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ImageUtil {
  public WritableImage changeColorOfBlackImage(javafx.scene.image.@NotNull Image img, int setArgb) {
    val w = (int) img.getWidth();
    val h = (int) img.getHeight();
    val reader = img.getPixelReader();

    val wImg = new WritableImage(w, h);
    val writer = wImg.getPixelWriter();
    for (int x = 0; x < w; ++x) {
      for (int y = 0; y < h; ++y) {
        val argb = reader.getArgb(x, y);
        if (argb != 0) {
          var color = reader.getColor(x, y);
          var r = (setArgb >> 16) & 0xff;
          var g = (setArgb >> 8) & 0xff;
          var b = (setArgb) & 0xff;
          writer.setArgb(
              x,
              y,
              ((int) color.getOpacity() * 255) << 24
                  | ((int) (r * (1 - color.getRed())) << 16)
                  | ((int) (g * (1 - color.getGreen())) << 8)
                  | (int) (b * (1 - color.getBlue())));
        } else {
          writer.setArgb(x, y, argb);
        }
      }
    }
    return wImg;
  }

  public @NotNull BufferedImage convertToBufferedImage(java.awt.Image awtImage) {
    if (awtImage instanceof BufferedImage) return (BufferedImage) awtImage;
    BufferedImage bImage =
        new BufferedImage(
            awtImage.getWidth(null), awtImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D bGr = bImage.createGraphics();
    bGr.drawImage(awtImage, 0, 0, null);
    bGr.dispose();
    return bImage;
  }
}
