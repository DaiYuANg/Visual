package org.visual.model.component.container;

import javafx.scene.paint.Color;
import org.visual.model.component.theme.Theme;

public class TransparentFusionPane extends FusionPane {
    public TransparentFusionPane() {
        super();
    }

    public TransparentFusionPane(boolean manuallyHandleOuterRegion) {
        super(manuallyHandleOuterRegion);
    }

    @Override
    protected AbstractFusionPane buildRootNode() {
        return new TransparentFusionPaneImpl();
    }

    protected class TransparentFusionPaneImpl extends FusionPaneImpl {
        @Override
        protected Color normalColor() {
            return Theme.current().transparentFusionPaneNormalBackgroundColor();
        }

        @Override
        protected Color hoverColor() {
            return Theme.current().transparentFusionPaneHoverBackgroundColor();
        }
    }
}
