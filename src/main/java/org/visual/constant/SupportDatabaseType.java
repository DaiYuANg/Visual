package org.visual.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SupportDatabaseType {
  MYSQL("MySQL"),
  SQLITE("Sqlite"),
  POSTGRES("Postgres");

  private final String databaseName;
}
