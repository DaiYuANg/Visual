package org.visual.debugger.controller;

import com.sun.tools.attach.VirtualMachine;
import jakarta.inject.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.visual.component.control.FontAwesomeIconicTextField;
import org.visual.debugger.component.SystemPropertiesListView;
import org.visual.debugger.context.VirtualMachineContext;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
public class OverviewController implements Initializable {

  @FXML FontAwesomeIconicTextField searchInput;

  @FXML VBox overviewRoot;

  @FXML SystemPropertiesListView systemPropertiesListView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    VirtualMachineContext.INSTANCE.addVirtualMachineList(
        (observable, oldValue, newValue) -> readJvm(newValue));
  }

  private void readJvm(@NotNull VirtualMachine jvm) {
    val values = System.getProperties().entrySet();
    systemPropertiesListView.getItems().addAll(values);
  }

  public void filterSystemProperties(KeyEvent keyEvent) {}
}
