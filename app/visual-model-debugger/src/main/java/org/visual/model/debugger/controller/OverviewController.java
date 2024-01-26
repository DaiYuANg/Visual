package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachine;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.component.SystemPropertiesListView;
import org.visual.model.debugger.context.VirtualMachineContext;

@Singleton
@Slf4j
public class OverviewController implements Initializable {

    @FXML
    VBox overviewRoot;

    @Inject
    Stage rootStage;

    @FXML
    SystemPropertiesListView systemPropertiesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VirtualMachineContext
                .INSTANCE
                .addVirtualMachineList((observable, oldValue, newValue) -> readJvm(newValue));
    }

    private void readJvm(@NotNull VirtualMachine jvm) {
        val values = System.getProperties().entrySet();
        systemPropertiesListView.getItems().addAll(values);
        ;
//        System.err.println(VirtualMachineContext.INSTANCE.getVirtualMachineProperties());
    }
}
