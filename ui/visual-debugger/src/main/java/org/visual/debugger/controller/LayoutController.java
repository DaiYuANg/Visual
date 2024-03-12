package org.visual.debugger.controller;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.context.LayoutContext;

@Slf4j
@Singleton
public class LayoutController implements Initializable {

  @FXML VBox firstSplit;

  @FXML SplitPane splitPane;

  //  @Inject Preferences preferences;

  //  @Inject PreferencesWrapper preferences;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    //    splitPane.setDividerPosition(
    //        0, preferences.getDouble(PreferencesKey.SPLIT_DIVIDER.getValue(), 0.2));
    LayoutContext.addCollapseListener(
        (observableValue, aBoolean, t1) -> {
          firstSplit.setVisible(!t1);
          if (t1) {
            splitPane.getItems().removeFirst();
          } else {
            splitPane.getItems().addFirst(firstSplit);
            splitPane.setDividerPositions(0.2, 0.8);
          }
        });
  }

  @SneakyThrows
  void onShutdown() {
    log.info("on shutdown");
    //    preferences.putDouble(
    //        PreferencesKey.SPLIT_DIVIDER.getValue(),
    // splitPane.getDividers().getFirst().getPosition());
    //    preferences.flush();
  }
}
