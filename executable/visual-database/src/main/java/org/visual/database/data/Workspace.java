package org.visual.database.data;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class Workspace implements Serializable {

  private String id = UUID.randomUUID().toString();

  private String name;
}
