package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachine;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.context.VirtualMachineContext;

@Singleton
@Slf4j
public class OverviewController implements Initializable {


    @FXML
    AnchorPane overviewRoot;

    @Inject
    Stage rootStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VirtualMachineContext
                .INSTANCE
                .addVirtualMachineList((observable, oldValue, newValue) -> readJvm(newValue));
    }

    @SneakyThrows
    private void readJvm(@NotNull VirtualMachine jvm) {
        System.err.println(VirtualMachineContext.INSTANCE.getVirtualMachineProperties());
    }
}
