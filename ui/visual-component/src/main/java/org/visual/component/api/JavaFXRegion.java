package org.visual.component.api;

import javafx.scene.layout.Region;

public interface JavaFXRegion extends JavaFXNode {
    @Override
    Region getSelfNode();
}