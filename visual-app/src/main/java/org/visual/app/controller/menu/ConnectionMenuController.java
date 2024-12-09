package org.visual.app.controller.menu;

import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.constant.ViewConstant;
import org.visual.app.ui.FXMLHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class ConnectionMenuController implements Initializable {

  private final FXMLHelper fxmlHelper;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void openConnectionForm() {
    val connectionDialog = new Dialog<Void>();
    connectionDialog.initStyle(StageStyle.UTILITY);
    val form = fxmlHelper.loadView(ViewConstant.DATABASE_CONNECT_FORM, DialogPane.class);
    connectionDialog.setDialogPane(form);
    connectionDialog.showAndWait();
  }
}
