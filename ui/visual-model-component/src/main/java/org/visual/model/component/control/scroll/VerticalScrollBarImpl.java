package org.visual.model.component.control.scroll;

import org.visual.model.component.shapes.VLine;
import org.visual.model.component.theme.Theme;

public class VerticalScrollBarImpl extends VLine {
    public VerticalScrollBarImpl() {
        super(VScrollPane.SCROLL_WIDTH);
        setStroke(Theme.current().scrollBarColor());
        setStart(0, VScrollPane.SCROLL_WIDTH / 2d);
    }

    public void setLength(double length) {
        setEnd(0, length - VScrollPane.SCROLL_WIDTH / 2d);
    }

    public double getLength() {
        return getEndY() + VScrollPane.SCROLL_WIDTH / 2d;
    }
}
