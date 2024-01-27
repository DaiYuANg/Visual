package org.visual.model.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.component.control.FontAwesomeSolidButton;
import org.visual.model.debugger.context.LayoutContext;

@Singleton
@Slf4j
public class ToolbarController implements Initializable {

    @FXML
    FontAwesomeSolidButton collapseButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toggleSplitPane() {
        LayoutContext.INSTANCE.toggleCollapse();
    }
}
