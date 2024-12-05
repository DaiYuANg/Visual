package org.visual.controller;

import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.ViewConstant;
import org.visual.util.FxmlLoaderHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class FileMenuController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void openSetting() {
    val dialog = new Dialog<Void>();
    dialog.initStyle(StageStyle.UTILITY);
    val pane = FxmlLoaderHelper.<DialogPane>load(ViewConstant.SETTING);
    dialog.setDialogPane(pane);
    dialog.showAndWait();
  }
}
