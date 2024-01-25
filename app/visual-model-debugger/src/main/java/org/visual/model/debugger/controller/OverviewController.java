package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachine;
import io.avaje.inject.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.context.DebuggerContext;
import org.visual.model.debugger.context.VirtualMachineContext;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

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
