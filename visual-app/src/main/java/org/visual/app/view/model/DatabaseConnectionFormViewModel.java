package org.visual.app.view.model;

import jakarta.inject.Singleton;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.visual.database.DBConnection;
import org.visual.database.SupportDatabaseType;

@Singleton
@Slf4j
@Getter
@SuppressWarnings("unused")
public class DatabaseConnectionFormViewModel {
  private final ObjectProperty<SupportDatabaseType> typeProperty = new SimpleObjectProperty<>(SupportDatabaseType.MYSQL);
  private final StringProperty hostProperty = new SimpleStringProperty();
  private final StringProperty portProperty = new SimpleStringProperty();
  private final StringProperty dbNameProperty = new SimpleStringProperty();
  private final StringProperty usernameProperty = new SimpleStringProperty();
  private final StringProperty passwordProperty = new SimpleStringProperty();

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

  public DBConnection toDatabaseConnectForm() {
    return DBConnection.builder()
      .type(getType())
      .host(getHost())
      .database(getDbName())
      .port(Integer.parseInt(getPort()))
      .username(getUsername())
      .password(getPassword())
      .build();
  }
}
