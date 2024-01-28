package org.visual.database.controller;

import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.component.display.VisualStage;
import org.visual.database.constant.FXMLKey;
import org.visual.database.context.VisualDatabaseContext;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class ConnectionController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openCreateConnection() {
        val load = VisualDatabaseContext.INSTANCE.load(FXMLKey.CREATE_CONNECTION);
        val dialog = new Dialog<>();
        dialog.setDialogPane((DialogPane) load);
        dialog.showAndWait();
    }
}
