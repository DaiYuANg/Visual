package org.visual.view.model;

import jakarta.inject.Singleton;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.visual.constant.SupportDatabaseType;
import org.visual.model.form.DatabaseConnectForm;
import org.visual.model.form.DatabaseConnectFormBuilder;

@Singleton
@Slf4j
@Getter
public class DatabaseConnectionFormViewModel {
  private final ObjectProperty<SupportDatabaseType> typeProperty = new SimpleObjectProperty<>();
  private final StringProperty hostProperty = new SimpleStringProperty();
  private final StringProperty portProperty = new SimpleStringProperty();
  private final StringProperty dbNameProperty = new SimpleStringProperty();
  private final StringProperty usernameProperty = new SimpleStringProperty();
  private final StringProperty passwordProperty = new SimpleStringProperty();

  // Getter 和 Setter
  public SupportDatabaseType getType() {
    return typeProperty.get();
  }

  public String getHost() {
    return hostProperty.get();
  }

  public void setHost(String host) {
    hostProperty.set(host);
  }

  public String getPort() {
    return portProperty.get();
  }

  public void setPort(String port) {
    portProperty.set(port);
  }

  public String getDbName() {
    return dbNameProperty.get();
  }

  public void setDbName(String dbName) {
    this.dbNameProperty.set(dbName);
  }

  public String getUsername() {
    return usernameProperty.get();
  }

  public String getPassword() {
    return passwordProperty.get();
  }

  public void setPassword(String password) {
    this.passwordProperty.set(password);
  }

  // 将数据转换为 DatabaseConnectForm
  public DatabaseConnectForm toDatabaseConnectForm() {
    return DatabaseConnectFormBuilder.builder()
      .type(getType())
      .host(getHost())
      .port(Integer.parseInt(getPort()))
      .username(getUsername())
      .password(getPassword())
      .build();
  }
}
