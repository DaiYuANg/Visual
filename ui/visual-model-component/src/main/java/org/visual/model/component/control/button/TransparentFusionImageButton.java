package org.visual.model.component.control.button;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.visual.model.component.theme.Theme;

public class TransparentFusionImageButton extends FusionImageButton {
    public TransparentFusionImageButton() {
    }

    public TransparentFusionImageButton(Image image) {
        super(image);
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
