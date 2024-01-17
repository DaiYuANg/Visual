package org.visual.model.database.api;


import lombok.SneakyThrows;
import org.visual.model.database.DatabaseTableColumn;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface VMDatabase {
    Set<DatabaseTableColumn> listTableColumns(String tableName);

    @SneakyThrows
    Set<String> listDatabase();

    @SneakyThrows
    Map<String, List<String>> listIndexes(String tableName);
}
