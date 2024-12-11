package org.visual.database;

import io.avaje.validation.Validator;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DatabaseConnectionService {

  private final Validator validator;

  @SneakyThrows
  public Uni<Boolean> checkConnectable(@NotNull DBConnection connection) {
    return Uni.createFrom().item(connection)
      .invoke(validator::validate)
      .log()
      .map(DBConnection::testConnect);
  }
}
