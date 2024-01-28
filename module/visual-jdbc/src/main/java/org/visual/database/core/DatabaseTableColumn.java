/* (C)2023*/
package org.visual.database.core;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Builder
@Getter
@ToString
public class DatabaseTableColumn {
  private final String columnName;
  private final String typeName;
  private final Integer columnSize;
  private final Integer nullable;
}
