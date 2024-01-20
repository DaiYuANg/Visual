/* (C)2024*/
package org.visual.model.database;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Builder
@ToString
@Accessors(chain = true)
@Getter
public class DatabaseArgument {
    private final String jdbcUrl;

    private final String username;

    private final String password;
}
