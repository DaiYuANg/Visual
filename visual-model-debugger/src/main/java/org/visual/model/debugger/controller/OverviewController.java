package org.visual.model.debugger.controller;

import io.avaje.inject.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.Debug;
import org.visual.model.debugger.context.DebuggerContext;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

@Singleton
public class OverviewController implements Initializable {


    @FXML
    AnchorPane overviewRoot;

    @Inject
    Stage rootStage;

    @PostConstruct
    void printAttachSys() {
        System.err.println(DebuggerContext.INSTANCE);
        DebuggerContext.INSTANCE.addVirtualMachineList((b, c, v) -> {
            System.err.println(v);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
