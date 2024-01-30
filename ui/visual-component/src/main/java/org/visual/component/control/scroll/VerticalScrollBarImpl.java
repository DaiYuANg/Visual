package org.visual.component.control.scroll;

import javafx.scene.paint.Color;
import org.visual.component.shapes.VLine;

public class VerticalScrollBarImpl extends VLine {
  public VerticalScrollBarImpl() {
    super(VScrollPane.SCROLL_WIDTH);
    setStroke(Color.RED);
    setStart(0, VScrollPane.SCROLL_WIDTH / 2d);
  }

  public void setLength(double length) {
    setEnd(0, length - VScrollPane.SCROLL_WIDTH / 2d);
  }

  public double getLength() {
    return getEndY() + VScrollPane.SCROLL_WIDTH / 2d;
  }
}
