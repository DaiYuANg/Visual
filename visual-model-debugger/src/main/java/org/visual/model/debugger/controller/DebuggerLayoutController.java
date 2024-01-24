package org.visual.model.debugger.controller;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.visual.model.debugger.component.SelectVirtualMachineDialog;
import org.visual.model.debugger.context.DebuggerContext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Slf4j
@Singleton
public class DebuggerLayoutController implements Initializable {

    @Inject
    Preferences preferences;

    @Inject
    SelectVirtualMachineDialog selectVirtualMachineDialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectVirtualMachineDialog.resultProperty().addListener((observable, oldValue, newValue) -> {
            onSelect(newValue);
        });
        if (DebuggerContext.INSTANCE.isNotAttached()) {
            selectVirtualMachineDialog.showAndWait();
        }
    }

    private void onSelect(@NotNull VirtualMachineDescriptor vm) {
        try {
            DebuggerContext.INSTANCE.setVirtualMachine(VirtualMachine.attach(vm.id()));
            selectVirtualMachineDialog.close();
        } catch (AttachNotSupportedException | IOException e) {
            selectVirtualMachineDialog.showAndWait();
            throw new RuntimeException(e);
        }
    }
}
