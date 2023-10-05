package org.visual.model.constants;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public enum InternalConfigKey {
    APPLICATION_NAME("application.name");

    private final String value;
}
