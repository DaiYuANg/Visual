package org.visual.app.controller.menu;

import io.vertx.mutiny.core.Vertx;
import jakarta.inject.Singleton;
import javafx.fxml.Initializable;
import javafx.scene.control.DialogPane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.app.constant.ViewConstant;
import org.visual.app.ui.DialogManager;
import org.visual.app.ui.FXMLHelper;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class HelpMenuController implements Initializable {

  private final FXMLHelper fxmlHelper;

  private final DialogManager dialogManager;

  private final Vertx vertx;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void showAbout() {
    val dialog = dialogManager.getDialog();
    dialog.setTitle("About");
    val aboutContent = fxmlHelper.load(ViewConstant.ABOUT, DialogPane.class);
    dialog.setDialogPane(aboutContent);
    dialog.showAndWait();
  }
}
