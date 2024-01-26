/* (C)2024*/
package org.visual.model.component.util;

import javafx.geometry.Point2D;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.component.shape.arrow.Arrow;

/**
 * Utils for drawing arrows. Used by connection and tail skins.
 */
public class ArrowUtils {

    /**
     * Draws the given arrow from the start to end points with the given offset from either end.
     *
     * @param arrow  an {@link Arrow} to be drawn
     * @param start  the start position
     * @param end    the end position
     * @param offset an offset from start and end positions
     */
    public static void draw(final @NotNull Arrow arrow, final @NotNull Point2D start, final @NotNull Point2D end, final double offset) {

        val deltaX = end.getX() - start.getX();
        val deltaY = end.getY() - start.getY();

        val angle = Math.atan2(deltaX, deltaY);

        val startX = start.getX() + offset * Math.sin(angle);
        val startY = start.getY() + offset * Math.cos(angle);

        val endX = end.getX() - offset * Math.sin(angle);
        val endY = end.getY() - offset * Math.cos(angle);

        arrow.setStart(startX, startY);
        arrow.setEnd(endX, endY);
        arrow.draw();

        arrow.setVisible(!(Math.hypot(deltaX, deltaY) < 2 * offset));
    }
}
