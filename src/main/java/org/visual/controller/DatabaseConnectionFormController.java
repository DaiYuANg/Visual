package org.visual.controller;

import jakarta.inject.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.visual.view.model.DatabaseConnectionFormViewModel;

import java.net.URL;
import java.util.ResourceBundle;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DatabaseConnectionFormController implements Initializable {

  private final DatabaseConnectionFormViewModel viewModel;

  @FXML
  private Button testConnect;
  @FXML
  private ComboBox<String> dbTypeComboBox;
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
    hostField.textProperty().bind(viewModel.getHostProperty());
    portField.textProperty().bind(viewModel.getPortProperty());
    dbNameField.textProperty().bind(viewModel.getDbNameProperty());
    usernameField.textProperty().bind(viewModel.getUsernameProperty());
    passwordField.textProperty().bind(viewModel.getPasswordProperty());
  }

  public void handleConnect(ActionEvent actionEvent) {

  }

  public void handleTestConnect(ActionEvent actionEvent) {

  }
}
