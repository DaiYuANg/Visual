package org.visual.model.controllers;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import com.google.inject.Inject;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.visual.model.services.IOperationSystemService;
import org.visual.model.services.IPreferenceService;

@Slf4j
public class WorkspaceController implements Initializable {
    @FXML
    public VBox rootVBox;

    @FXML
    public BorderPane layoutPane;

    @Inject
    private Vertx vertx;

    @Inject
    private EventBus eventBus;

    @Inject
    private IOperationSystemService operationSystemService;

    @Inject
    private IPreferenceService preferenceService;

    @Inject
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventBus.consumer("test", event -> {
            log.info(event.body().toString());
        });
    }
}
