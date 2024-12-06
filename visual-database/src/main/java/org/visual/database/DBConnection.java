package org.visual.database;

import io.avaje.validation.constraints.Max;
import io.avaje.validation.constraints.Min;
import io.avaje.validation.constraints.NotBlank;
import io.avaje.validation.constraints.Valid;
import io.vavr.control.Try;
import lombok.*;
import org.apache.commons.text.StringSubstitutor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.net.ConnectException;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Optional;

@Builder
@Valid
@Data
public class DBConnection {

  @NonNull
  private SupportDatabaseType type;
  @NotBlank
  private String host;
  @Min(1)
  @Max(65535)
  private Integer port;
  @NotBlank
  private String database;
  @NotBlank
  private String username;
  @NotBlank
  private String password;

  @Contract(" -> new")
  private @NotNull @Unmodifiable Map<String, String> getReplaceValueMap() {
    return Map.of(
      "host", host,
      "port", String.valueOf(port),
      "database", database
    );
  }

  public String buildConnectionUrl() {
    val templateStr = switch (type) {
      case MYSQL -> "jdbc:mysql://${host}:${port}/${database}";
      case POSTGRES -> "jdbc:postgresql://${host}:${port}/${database}";
      case SQLITE -> "jdbc:sqlite://${host}:${port};databaseName=${database}";
    };

    val valuesMap = getReplaceValueMap();
    val substitutor = new StringSubstitutor(valuesMap);
    return substitutor.replace(templateStr);
  }

  @SneakyThrows
  public Boolean testConnect() {
    return Try.of(this::buildConnectionUrl)
      .mapTry(url -> {
        // 使用 try-with-resources 确保 Connection 被关闭
        @Cleanup val connection = DriverManager.getConnection(buildConnectionUrl(), username, password);
        return Optional.ofNullable(connection.getMetaData()).map(m -> true).orElse(false);
      })
      .recover(ConnectException.class, false)  // 如果捕获到连接异常，返回 false
      .get();
  }
}
