package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachineDescriptor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.context.LayoutContext;

@Slf4j
@Singleton
public class LayoutController implements Initializable {

    @FXML
    VBox firstSplit;
    @FXML
    SplitPane splitPane;
    @Inject
    Preferences preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LayoutContext.INSTANCE.addCollapseListener((observableValue, aBoolean, t1) -> {
            firstSplit.setVisible(!t1);
            if (t1) {
                splitPane.setDividerPosition(0, 0);
                splitPane.setDividerPosition(1, 1);
            } else {
                splitPane.setDividerPosition(0, 0.2);
                splitPane.setDividerPosition(1, 0.8);
            }
        });

    }

    private void onSelect(@NotNull VirtualMachineDescriptor vm) {
    }
}
