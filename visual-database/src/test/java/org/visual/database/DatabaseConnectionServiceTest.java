package org.visual.database;

import io.avaje.inject.test.InjectTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith(MockitoExtension.class)
@Slf4j
@InjectTest
@RequiredArgsConstructor
class DatabaseConnectionServiceTest {

  @Mock
  DatabaseConnectionService connectionService;

  @Container
  private static final MySQLContainer<?> mysql =
    new MySQLContainer<>("mysql:latest")
      .withDatabaseName("testdb")
      .withUsername("testuser")
      .withPassword("testpass");

  @Test
  void checkConnectable() {
    val dbc = DBConnection.builder().database(mysql.getDatabaseName())
      .type(SupportDatabaseType.MYSQL)
      .host(mysql.getHost())
      .port(mysql.getFirstMappedPort())
      .password(mysql.getPassword())
      .username(mysql.getUsername())
      .build();
    val isConnectable = connectionService.checkConnectable(dbc);
    Assertions.assertTrue(isConnectable);
  }
}