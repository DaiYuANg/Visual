package org.visual.debugger.controller;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.visual.debugger.constant.PreferencesKey;
import org.visual.debugger.context.LayoutContext;
import org.visual.shared.PreferencesWrapper;

@Slf4j
@Singleton
public class LayoutController implements Initializable {

  @FXML VBox firstSplit;

  @FXML SplitPane splitPane;

  @Inject PreferencesWrapper preferences;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    splitPane.setDividerPosition(
        0, Objects.requireNonNull(preferences.get(PreferencesKey.SPLIT_DIVIDER.getValue(), 0.2)));
    LayoutContext.INSTANCE.addCollapseListener(
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

  void onShutdown() {
    log.info("on shutdown");
    preferences.put(
        PreferencesKey.SPLIT_DIVIDER.getValue(), splitPane.getDividers().getFirst().getPosition());
    preferences.flush();
  }
}
