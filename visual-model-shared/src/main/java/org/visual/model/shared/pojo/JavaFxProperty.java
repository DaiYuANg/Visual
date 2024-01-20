package org.visual.model.shared.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class JavaFxProperty {
    private int Version;
    private String RuntimeVersion;
    private int RuntimeBuild;
}
