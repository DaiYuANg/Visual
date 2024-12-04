package org.visual.factory;

import static org.hibernate.cfg.JdbcSettings.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.Lazy;
import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;
import org.visual.entity.History;

@Factory
@Lazy
public class Hibernate {

  @Bean
  SessionFactory sessionFactory() {
    val connectionUrl = "jdbc:h2:./visual.db";
    val sessionFactory =
      new Configuration()
        .addAnnotatedClass(History.class)
        // use H2 in-memory database
        .setProperty(JAKARTA_JDBC_URL, connectionUrl)
        .setProperty(JAKARTA_JDBC_USER, "sa")
        .setProperty(JAKARTA_JDBC_PASSWORD, "")
        .setProperty(AvailableSettings.JAKARTA_HBM2DDL_DATABASE_ACTION, Action.UPDATE)
        .setProperty(AvailableSettings.JTA_PLATFORM, true)
        // display SQL in console
        .setProperty(LOG_SLOW_QUERY, 1)
        .setProperty(SHOW_SQL, true)
        .setProperty(FORMAT_SQL, true)
        .setProperty(HIGHLIGHT_SQL, true)
        .buildSessionFactory();
    sessionFactory.getSchemaManager().validateMappedObjects();
    return sessionFactory;
  }

  @Bean
  JPAQueryFactory jpaQueryFactory(SessionFactory sessionFactory) {
    val entityManager = sessionFactory.openSession();
    return new JPAQueryFactory(entityManager);
  }
}
