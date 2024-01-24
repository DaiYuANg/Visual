package org.visual.model.component.control.button;

import javafx.scene.paint.Color;
import org.visual.model.component.container.AbstractFusionPane;
import org.visual.model.component.theme.Theme;

public abstract class AbstractFusionButton extends AbstractFusionPane {
    protected abstract void onMouseClicked();

    @Override
    protected Color normalColor() {
        return Theme.current().fusionButtonNormalBackgroundColor();
    }

    @Override
    protected Color hoverColor() {
        return Theme.current().fusionButtonHoverBackgroundColor();
    }

    @Override
    protected Color downColor() {
        return Theme.current().fusionButtonDownBackgroundColor();
    }
}
