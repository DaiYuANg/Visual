package org.visual.controller.creation;

import jakarta.inject.Singleton;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.visual.constant.AvailableFeature;
import org.visual.context.CreationContext;

@Singleton
@Slf4j
public class CreationProjectController implements Initializable {

  @FXML public ListView<AvailableFeature> rootListView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    val features = Arrays.stream(AvailableFeature.values()).toList();
    rootListView.getItems().addAll(features);
    rootListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    rootListView.getSelectionModel().select(0);
    rootListView
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observableValue, s, newValue) -> {
              log.atTrace().log("Feature:{}", newValue);
              CreationContext.INSTANCE.set(newValue);
            });
  }
}
