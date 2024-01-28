package org.visual.component.wrapper;

import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BufferedImageBox implements ImageBox {
    private final BufferedImage bImg;

    @Override
    public int getWidth() {
        return bImg.getWidth();
    }

    @Override
    public int getHeight() {
        return bImg.getHeight();
    }

    @Override
    public int getRGB(int x, int y) {
        return bImg.getRGB(x, y);
    }

    @Override
    public CanvasBox createGraphics() {
        return new BufferedImageCanvasBox(bImg, bImg.createGraphics());
    }

    @Override
    public Image toFXImage() {
        return SwingFXUtils.toFXImage(bImg, null);
    }
}
