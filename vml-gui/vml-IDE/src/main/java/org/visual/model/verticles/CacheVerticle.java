package org.visual.model.verticles;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import dev.dirs.BaseDirectories;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.annotation.DbMigration;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.datasource.InitDatabase;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Singleton
public class CacheVerticle extends AbstractVerticle {
    private final String cacheRoot;

    @Inject
    public CacheVerticle(@Named("ApplicationCache") String cacheRoot) {
       this.cacheRoot = cacheRoot;
    }

    @Override
    public void init(Vertx vertx, Context context) {

    }
}
