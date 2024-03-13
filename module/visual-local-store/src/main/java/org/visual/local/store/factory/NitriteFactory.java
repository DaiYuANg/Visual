package org.visual.local.store.factory;

import static java.lang.Boolean.TRUE;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;
import org.visual.local.store.entity.History;

@Factory
@Slf4j
public class NitriteFactory {

  @Bean
  SessionFactory sessionFactory() {
    return new Configuration()
        .addAnnotatedClass(History.class)
        // PostgreSQL
        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:h2:./visual.db")
        // Credentials
        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "sa")
        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
        // Automatic schema export
        .setProperty(
            AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION, Action.SPEC_ACTION_DROP_AND_CREATE)
        // SQL statement logging
        .setProperty(AvailableSettings.SHOW_SQL, TRUE.toString())
        .setProperty(AvailableSettings.FORMAT_SQL, TRUE.toString())
        .setProperty(AvailableSettings.HIGHLIGHT_SQL, TRUE.toString())
        // Create a new SessionFactory
        .buildSessionFactory();
  }
}
