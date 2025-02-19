package org.visual.app.controller.menu;

import io.avaje.inject.Lazy;
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
import java.util.concurrent.Executor;

@Singleton
@Slf4j
@RequiredArgsConstructor
@Lazy
public class DatabaseConnectionFormController implements Initializable {

  private final DatabaseConnectionFormViewModel viewModel;

  private final DatabaseConnectionService databaseConnectionService;

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
  private final Executor executor;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
//    log.atInfo().log("Theme,{}", applicationContext.get("DARK", Boolean.class));
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

  }

  public void handleTestConnect() {
    testConnect.setVisible(false);
    testConnect.setManaged(false);
    progressIndicator.setVisible(true);
    val pause = new PauseTransition(Duration.seconds(3));
    pause.setOnFinished(e -> {
      progressIndicator.setVisible(false); // 隐藏 ProgressIndicator
      testConnect.setVisible(true); // 启用按钮
      testConnect.setManaged(true);
      testConnect.setText("Done");
    });
    pause.play();
    val form = viewModel.toDatabaseConnectForm();
    log.atInfo().log("Connect Form:{}", form);
    databaseConnectionService.checkConnectable(form)
      .subscribe()
      .with(t -> {
        pause.stop();
        log.atInfo().log("Is success:{}", t);
      });
  }
}
