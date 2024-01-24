package org.visual.model.component.util;

import javafx.scene.paint.Color;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorUtil {
    public static float[] toHSB(Color color) {
        float[] ff = new float[3];
        java.awt.Color.RGBtoHSB((int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255), ff);
        return ff;
    }

    public static Color fromHSB(float[] hsb, double alpha) {
        var rgb = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        var r = (rgb >> 16) & 0xff;
        var g = (rgb >> 8) & 0xff;
        var b = rgb & 0xff;
        return new Color(r / 255d, g / 255d, b / 255d, alpha);
    }

    public static Color fromHSB(float h, float s, float b, double alpha) {
        return fromHSB(new float[]{h, s, b}, alpha);
    }
}
