package org.visual.controller;

import io.avaje.inject.Lazy;
import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.ViewConstant;
import org.visual.ui.AboutDialog;
import org.visual.util.FxmlLoaderHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Singleton
@Lazy
@RequiredArgsConstructor
public class GlobalMenuController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }



  public void openConnectionForm(ActionEvent actionEvent) {

  }
}
