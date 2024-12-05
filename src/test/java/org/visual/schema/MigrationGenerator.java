package org.visual.schema;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

import java.io.IOException;

public class MigrationGenerator {
  /**
   * Generate the next "DB schema DIFF" migration.
   */
  public static void main(String[] args) throws IOException {

    DbMigration dbMigration = DbMigration.create();
    dbMigration.setPlatform(Platform.H2);

    dbMigration.generateMigration();
  }
}
