/* (C)2024*/
package org.visual.model.database.api;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.visual.model.database.core.DatabaseTableColumn;

public interface VMDatabase {
    Set<DatabaseTableColumn> listTableColumns(String tableName);

    Set<String> listDatabase();

    Map<String, List<String>> listIndexes(String tableName);
}
