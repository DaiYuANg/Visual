package org.visual.database;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.visual.database.driver.DriverManager;

public class DriverManagerTest {

  @Test
  void testDriverList() {
    val dm = new DriverManager();
    System.err.println(dm);
  }
}
