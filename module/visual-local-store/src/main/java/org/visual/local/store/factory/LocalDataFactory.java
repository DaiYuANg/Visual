package org.visual.local.store.factory;

import static java.lang.Boolean.TRUE;
import static org.hibernate.cfg.JdbcSettings.*;
import static org.hibernate.cfg.SchemaToolingSettings.HBM2DDL_AUTO;
import static org.hibernate.cfg.SchemaToolingSettings.JAKARTA_HBM2DDL_DATABASE_ACTION;
import static org.hibernate.tool.schema.Action.ACTION_UPDATE;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.dirs.BaseDirectories;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;
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
            .setProperty(JAKARTA_JDBC_URL, url)
            .setProperty(JAKARTA_JDBC_USER, "sa")
            .setProperty(JAKARTA_JDBC_PASSWORD, "")
            .setProperty(JAKARTA_HBM2DDL_DATABASE_ACTION, ACTION_UPDATE)
            .setProperty(HBM2DDL_AUTO, ACTION_UPDATE)
            .setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy())
            // display SQL in console
            .setProperty(SHOW_SQL, TRUE.toString())
            .setProperty(FORMAT_SQL, TRUE.toString())
            .setProperty(HIGHLIGHT_SQL, TRUE.toString())
            .buildSessionFactory();
    sessionFactory.getSchemaManager().exportMappedObjects(true);
    return sessionFactory;
  }

  @Bean
  EntityManager entityManager(@NotNull SessionFactory sessionFactory) {
    return sessionFactory.createEntityManager();
  }

  @Bean
  JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }
}
