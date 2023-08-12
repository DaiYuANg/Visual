package org.visual.model.components;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BaseContainer extends Pane {

    public void addNode(Node... nodes){
        getChildren().addAll(nodes);
    }
}
