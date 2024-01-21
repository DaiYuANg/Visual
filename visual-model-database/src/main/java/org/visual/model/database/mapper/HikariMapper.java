/* (C)2024*/
package org.visual.model.database.mapper;

import com.zaxxer.hikari.HikariConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.visual.model.database.core.DatabaseArgument;

@Mapper
public interface HikariMapper {
    HikariMapper INSTANCE = Mappers.getMapper(HikariMapper.class);

    @Mapping(target = "validationTimeout", ignore = true)
    @Mapping(target = "transactionIsolation", ignore = true)
    @Mapping(target = "threadFactory", ignore = true)
    @Mapping(target = "schema", ignore = true)
    @Mapping(target = "scheduledExecutor", ignore = true)
    @Mapping(target = "registerMbeans", ignore = true)
    @Mapping(target = "readOnly", ignore = true)
    @Mapping(target = "poolName", ignore = true)
    @Mapping(target = "minimumIdle", ignore = true)
    @Mapping(target = "metricsTrackerFactory", ignore = true)
    @Mapping(target = "metricRegistry", ignore = true)
    @Mapping(target = "maximumPoolSize", ignore = true)
    @Mapping(target = "maxLifetime", ignore = true)
    @Mapping(target = "leakDetectionThreshold", ignore = true)
    @Mapping(target = "keepaliveTime", ignore = true)
    @Mapping(target = "isolateInternalQueries", ignore = true)
    @Mapping(target = "initializationFailTimeout", ignore = true)
    @Mapping(target = "idleTimeout", ignore = true)
    @Mapping(target = "healthCheckRegistry", ignore = true)
    @Mapping(target = "healthCheckProperties", ignore = true)
    @Mapping(target = "exceptionOverrideClassName", ignore = true)
    @Mapping(target = "driverClassName", ignore = true)
    @Mapping(target = "dataSourceProperties", ignore = true)
    @Mapping(target = "dataSourceJNDI", ignore = true)
    @Mapping(target = "dataSourceClassName", ignore = true)
    @Mapping(target = "dataSource", ignore = true)
    @Mapping(target = "connectionTimeout", ignore = true)
    @Mapping(target = "connectionTestQuery", ignore = true)
    @Mapping(target = "connectionInitSql", ignore = true)
    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "autoCommit", ignore = true)
    @Mapping(target = "allowPoolSuspension", ignore = true)
    HikariConfig buildConfig(DatabaseArgument databaseArgument);
}
