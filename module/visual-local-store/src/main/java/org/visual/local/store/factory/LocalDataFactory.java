package org.visual.local.store.factory;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.JdbcSettings.*;

import dev.dirs.BaseDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.visual.local.store.entity.History;

@Factory
@Slf4j
public class LocalDataFactory {

  @Bean
  SessionFactory sessionFactory() {
    val url = STR."jdbc:h2:\{BaseDirectories.get().dataLocalDir}visual.db";
    val sessionFactory =
        new Configuration()
            .addAnnotatedClass(History.class)
            // use H2 in-memory database
            .setProperty(JAKARTA_JDBC_URL, url)
            .setProperty(JAKARTA_JDBC_USER, "sa")
            .setProperty(JAKARTA_JDBC_PASSWORD, "")
            // use Agroal connection pool
            .setProperty("hibernate.agroal.maxSize", "20")
            // display SQL in console
            .setProperty(SHOW_SQL, TRUE.toString())
            .setProperty(FORMAT_SQL, TRUE.toString())
            .setProperty(HIGHLIGHT_SQL, TRUE.toString())
            .buildSessionFactory();
    sessionFactory.getSchemaManager().exportMappedObjects(true);
    return sessionFactory;
  }
}
