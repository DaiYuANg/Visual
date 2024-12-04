package org.visual.controller;

import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.component.AboutDialog;
import org.visual.constant.ViewConstant;
import org.visual.util.FxmlLoaderHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HelpMenuController implements Initializable {
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void showAbout() {
    val aboutDialog = new AboutDialog();
    val aboutContent = FxmlLoaderHelper.<DialogPane>load(ViewConstant.ABOUT);
    aboutDialog.setDialogPane(aboutContent);
    aboutDialog.showAndWait();
  }
}
