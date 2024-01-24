package org.visual.model.component.control.button;

import javafx.scene.paint.Color;
import org.visual.model.component.theme.Theme;

public class TransparentFusionButton extends FusionButton {
    public TransparentFusionButton() {
        setOnlyAnimateWhenNotClicked(true);
    }

    public TransparentFusionButton(String text) {
        super(text);
        setOnlyAnimateWhenNotClicked(true);
    }

    @Override
    protected Color normalColor() {
        return Theme.current().transparentFusionButtonNormalBackgroundColor();
    }

    @Override
    protected Color hoverColor() {
        return Theme.current().transparentFusionButtonHoverBackgroundColor();
    }

    @Override
    protected Color downColor() {
        return Theme.current().transparentFusionButtonDownBackgroundColor();
    }
}
