package org.visual.local.store;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;
import java.io.IOException;

public class DBMigration {
  public static void main(String[] args) throws IOException {
    DbMigration dbMigration = DbMigration.create();
    dbMigration.setPlatform(Platform.H2);
    dbMigration.generateMigration();
  }
}
