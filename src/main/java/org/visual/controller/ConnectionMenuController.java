package org.visual.controller;

import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.ViewConstant;
import org.visual.util.FxmlLoaderHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@RequiredArgsConstructor
@Singleton
public class ConnectionMenuController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void openConnectionForm() {
    val connectionDialog = new Dialog<Void>();
    val form = FxmlLoaderHelper.<DialogPane>load(ViewConstant.DATABASE_CONNECT_FORM);
    connectionDialog.setDialogPane(form);
    connectionDialog.showAndWait();
  }
}
