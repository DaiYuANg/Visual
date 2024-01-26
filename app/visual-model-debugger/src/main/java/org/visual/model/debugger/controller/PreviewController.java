package org.visual.model.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class PreviewController implements Initializable {
    public TreeView<String> componentTree;
    public StackPane showComponent;

    private final TreeItem<String> componentTreeRoot = new TreeItem<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        componentTree.setRoot(componentTreeRoot);
    }
}
