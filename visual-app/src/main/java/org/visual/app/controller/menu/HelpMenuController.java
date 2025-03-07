package org.visual.app.controller.menu;

import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.constant.ViewConstant;
import org.visual.app.util.DialogManager;
import org.visual.app.util.FXMLHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HelpMenuController implements Initializable {

  private final FXMLHelper fxmlHelper;

  private final DialogManager dialogManager;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void showAbout() {
    val dialog = dialogManager.getDialog();
    dialog.setTitle("About");
    val aboutContent = fxmlHelper.loadView(ViewConstant.ABOUT, DialogPane.class);
    dialog.setDialogPane(aboutContent);
    dialog.showAndWait();
  }
}
