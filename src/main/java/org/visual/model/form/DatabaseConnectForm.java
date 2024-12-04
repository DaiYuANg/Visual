package org.visual.model.form;

import io.soabase.recordbuilder.core.RecordBuilder;
import org.visual.constant.SupportDatabaseType;

@RecordBuilder
public record DatabaseConnectForm(
  SupportDatabaseType type,
  String host,
  Integer port,
  String database,
  String username,
  String password
) {
}
