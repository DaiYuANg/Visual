/* (C)2024*/
package org.visual.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.visual.constant.FXMLView;
import org.visual.context.UIContext;
import org.visual.local.store.api.HistoryRepository;

@Singleton
@RequiredArgsConstructor
public class MainLayoutController implements Initializable {
  private final HistoryRepository historyRepository;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val isHasHistory = historyRepository.findLatestHistory();
    if (isHasHistory.isEmpty()) {
      val dialog = new Dialog<String>();
      dialog.setResizable(true);
      val creation = UIContext.INSTANCE.load(FXMLView.CREATION);
      dialog.getDialogPane().setContent(creation);
      dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
      dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
      dialog.showAndWait();
    }
  }
}
