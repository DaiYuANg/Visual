/* (C)2024*/
package org.visual.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.visual.component.GuideDialog;
import org.visual.context.DIContext;
import org.visual.local.store.api.HistoryRepository;

@Singleton
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MainLayoutController implements Initializable {
  private final HistoryRepository historyRepository;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val isHasHistory = historyRepository.findLatestHistory();
    if (isHasHistory.isEmpty()) {
      val dialog = DIContext.INSTANCE.get(GuideDialog.class);
      dialog.showAndWait();
    }
  }
}
