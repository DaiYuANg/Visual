package org.visual.model.component.preview.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class PreviewController implements Initializable {
    public TreeView<String> componentTree;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Root");
        TreeItem<String> child1 = new TreeItem<>("Child 1");
        TreeItem<String> child2 = new TreeItem<>("Child 2");
        rootItem.getChildren().addAll(child1, child2);
        componentTree.setRoot(rootItem);
    }
}
