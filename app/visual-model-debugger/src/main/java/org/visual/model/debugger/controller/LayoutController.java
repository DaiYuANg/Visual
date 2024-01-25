package org.visual.model.debugger.controller;

import com.sun.tools.attach.VirtualMachineDescriptor;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

@Slf4j
@Singleton
public class LayoutController implements Initializable {

    @Inject
    Preferences preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        selectVirtualMachineDialog.resultProperty().addListener((observable, oldValue, newValue) -> {
//            onSelect(newValue);
//        });
//        if (VirtualMachineContext.INSTANCE.isNotAttached()) {
//            selectVirtualMachineDialog.showAndWait();
//        }
    }

    private void onSelect(@NotNull VirtualMachineDescriptor vm) {
//        try {
//            VirtualMachineContext.INSTANCE.setVirtualMachine(VirtualMachine.attach(vm.id()));
//            selectVirtualMachineDialog.close();
//        } catch (AttachNotSupportedException | IOException e) {
//            selectVirtualMachineDialog.showAndWait();
//            throw new RuntimeException(e);
//        }
    }
}
