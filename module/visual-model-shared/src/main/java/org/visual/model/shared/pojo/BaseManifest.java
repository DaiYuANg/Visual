package org.visual.model.shared.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseManifest {
    private String version;

    private String gitHash;

    private String lastTag;

    private String branch;
}
