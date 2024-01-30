package org.visual.database.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.collaborative.server.InternalServer;
import org.visual.component.control.UndecoratedDialog;
import org.visual.database.constant.FXMLKey;
import org.visual.database.context.VisualDatabaseContext;

@Singleton
@Slf4j
public class ConnectionController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  public void openCreateConnection() {
    val load = VisualDatabaseContext.INSTANCE.load(FXMLKey.CREATE_CONNECTION);
    val dialog = new UndecoratedDialog<>();
    dialog.setDialogPane((DialogPane) load);
    dialog.showAndWait();
  }

  public void createServer() {
    Thread.ofVirtual().start(() -> new InternalServer().start());
  }
}