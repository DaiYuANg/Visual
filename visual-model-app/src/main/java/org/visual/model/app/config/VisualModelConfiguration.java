package org.visual.model.app.config;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class VisualModelConfiguration {

    private UIConfiguration uiConfiguration;
}
