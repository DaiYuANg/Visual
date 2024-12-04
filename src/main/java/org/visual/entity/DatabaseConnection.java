package org.visual.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.visual.constant.SupportDatabaseType;

@Entity
@Table
@Getter
@Setter
@Accessors(chain = true)
public class DatabaseConnection extends BaseEntity {
  private SupportDatabaseType databaseType;

  private String host;

  private Integer port;

  private String database;

  private String username;

  private String password;
}
