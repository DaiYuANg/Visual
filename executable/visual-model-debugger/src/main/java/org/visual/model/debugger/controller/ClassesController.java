package org.visual.model.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ClassesController implements Initializable {


    @FXML
    TreeView<String> fileView;

    @FXML
    TextArea codeArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SplitPane.setResizableWithParent(fileView, true);
    }
}
