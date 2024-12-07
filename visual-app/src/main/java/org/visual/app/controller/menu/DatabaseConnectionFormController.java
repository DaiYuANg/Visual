package org.visual.app.controller.menu;

import io.avaje.inject.Lazy;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.inject.Singleton;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.visual.app.context.ApplicationContext;
import org.visual.app.view.model.DatabaseConnectionFormViewModel;
import org.visual.database.DatabaseConnectionService;
import org.visual.database.SupportDatabaseType;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
@Lazy
public class DatabaseConnectionFormController implements Initializable {

  private final DatabaseConnectionFormViewModel viewModel;

  private final DatabaseConnectionService databaseConnectionService;

  private final Uni<EventBus> eventBus;

  private final ApplicationContext applicationContext;
  @FXML
  private DialogPane p;
  @FXML
  private ProgressIndicator progressIndicator;
  @FXML
  private Button testConnect;
  @FXML
  private ComboBox<SupportDatabaseType> dbTypeComboBox;
  @FXML
  private TextField hostField;
  @FXML
  private TextField portField;
  @FXML
  private TextField dbNameField;
  @FXML
  private TextField usernameField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button connectButton;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    log.atInfo().log("Theme,{}", applicationContext.get("DARK", Boolean.class));
    val validationSupport = new ValidationSupport();
    validationSupport.registerValidator(hostField, Validator.createEmptyValidator("Host is required."));
    val dbTypes = FXCollections.observableArrayList(SupportDatabaseType.values());
    dbTypeComboBox.setItems(dbTypes);
    dbTypeComboBox.setValue(SupportDatabaseType.MYSQL);
    dbTypeComboBox.setPromptText("Select a database type");
    dbTypeComboBox.valueProperty().bindBidirectional(viewModel.getTypeProperty());
    hostField.textProperty().bindBidirectional(viewModel.getHostProperty());
    portField.textProperty().bindBidirectional(viewModel.getPortProperty());
    dbNameField.textProperty().bindBidirectional(viewModel.getDbNameProperty());
    usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
    passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
  }

  public void handleConnect() {
    eventBus.invoke(eb -> {
        eb.send("closeDialog", "1");
      })
      .subscribe().with(t -> {
        log.atInfo().log(t.toString());
      });
  }

  public void handleTestConnect() {
    testConnect.setVisible(false);
    testConnect.setManaged(false);
    // 显示 ProgressIndicator
    progressIndicator.setVisible(true);
    // 模拟加载过程，使用 PauseTransition 延迟操作
    val pause = new PauseTransition(Duration.seconds(3));
    pause.setOnFinished(e -> {
      // 加载完成后
      progressIndicator.setVisible(false); // 隐藏 ProgressIndicator
      testConnect.setVisible(true); // 启用按钮
      testConnect.setManaged(true);
      testConnect.setText("Done");
    });
    pause.play();
    val form = viewModel.toDatabaseConnectForm();
    log.atInfo().log("Connect Form:{}", form);
    val isConnectSuccess = databaseConnectionService.checkConnectable(form);
  }
}
