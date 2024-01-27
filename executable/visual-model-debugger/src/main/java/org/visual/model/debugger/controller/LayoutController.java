package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachineDescriptor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.constant.PreferencesKey;
import org.visual.model.debugger.context.LayoutContext;
import org.visual.model.shared.PreferencesWrapper;

@Slf4j
@Singleton
public class LayoutController implements Initializable {

    @FXML
    VBox firstSplit;

    @FXML
    SplitPane splitPane;

    @Inject
    PreferencesWrapper preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        splitPane.getDividers().getFirst().positionProperty().addListener((observable, oldValue, newValue) -> {
            preferences.put(PreferencesKey.SPLIT_DIVIDER.getValue(), newValue.toString());
            log.info("listener:{}", newValue);
            preferences.flush();
        });
        LayoutContext.INSTANCE.addCollapseListener((observableValue, aBoolean, t1) -> {
            firstSplit.setVisible(!t1);
            if (t1) {
                splitPane.getItems().removeFirst();
            } else {
                splitPane.getItems().addFirst(firstSplit);
                splitPane.setDividerPositions(0.2, 0.8);
            }
        });
    }

    private void onSelect(@NotNull VirtualMachineDescriptor vm) {
    }
}
