package org.visual.debugger.controller;

import com.sun.tools.attach.VirtualMachine;
import jakarta.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.visual.debugger.component.VirtualMachineDescriptorListView;
import org.visual.debugger.context.VirtualMachineContext;

@Singleton
@Slf4j
public class VirtualMachineListController implements Initializable {

  @FXML HBox root;

  @FXML VirtualMachineDescriptorListView list;

  @FXML TextField searchInput;

  private final PauseTransition pause = new PauseTransition(Duration.millis(500));

  {
    pause.setOnFinished(
        event -> {
          list.getItems().clear();
          val input = searchInput.getText();
          if (StringUtils.isEmpty(input)) {
            list.getItems().addAll(VirtualMachine.list());
          }
          val filtered =
              list.getItems().stream()
                  .filter(
                      item -> {
                        val isNameContains = item.displayName().contains(input);
                        val isIdContains = item.id().contains(input);
                        return isNameContains || isIdContains;
                      })
                  .toList();
          list.getItems().addAll(filtered);
        });
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    SplitPane.setResizableWithParent(root, true);
    list.getItems().addAll(VirtualMachine.list());
  }

  @SneakyThrows
  public void setCurrentVirtualMachineContext(MouseEvent e) {
    log.info("event:{}", e);
    val item = list.getSelectionModel().getSelectedItem();
    val jvm = VirtualMachine.attach(item);
    VirtualMachineContext.INSTANCE.setVirtualMachine(jvm);
  }

  public void filterVirtualMachines() {
    pause.stop();
    pause.playFromStart();
  }
}
