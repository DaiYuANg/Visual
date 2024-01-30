package org.visual.component.control.scroll;

import javafx.scene.paint.Color;
import org.visual.component.shapes.VLine;

public class HorizontalScrollBarImpl extends VLine {
  public HorizontalScrollBarImpl() {
    super(VScrollPane.SCROLL_WIDTH);
    setStroke(Color.ALICEBLUE);
    setStart(VScrollPane.SCROLL_WIDTH / 2d, 0);
  }

  public void setLength(double length) {
    setEnd(length - VScrollPane.SCROLL_WIDTH / 2d, 0);
  }

  public double getLength() {
    return getEndX() + VScrollPane.SCROLL_WIDTH / 2d;
  }
}
