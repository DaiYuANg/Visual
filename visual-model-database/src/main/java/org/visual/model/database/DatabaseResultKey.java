package org.visual.model.database;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DatabaseResultKey {
    TABLE_CAT("TABLE_CAT"),
    COLUMN_NAME("COLUMN_NAME"),
    TYPE_NAME("TYPE_NAME"),
    COLUMN_SIZE("COLUMN_SIZE"),
    INDEX_NAME("INDEX_NAME"),
    NULLABLE("NULLABLE");

    private final String key;
}
