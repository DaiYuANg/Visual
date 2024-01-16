package org.visual.model.database.api;


import lombok.SneakyThrows;
import org.visual.model.database.DatabaseTableColumn;

import java.util.Set;

public interface Database {
    @SneakyThrows
    Set<DatabaseTableColumn> listTableColumns(String tableName);
}
