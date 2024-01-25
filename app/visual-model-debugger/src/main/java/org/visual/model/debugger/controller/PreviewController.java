package org.visual.model.debugger.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
