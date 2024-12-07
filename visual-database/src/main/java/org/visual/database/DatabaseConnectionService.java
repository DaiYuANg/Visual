package org.visual.database;

import io.avaje.validation.Validator;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DatabaseConnectionService {

  private final Validator validator;

  @SneakyThrows
  public Boolean checkConnectable(@NotNull DBConnection connection) {
    validator.validate(connection);
    val url = connection.buildConnectionUrl();
    log.atInfo().log("URL:{}", url);
    return connection.testConnect();
  }
}
