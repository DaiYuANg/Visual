package org.visual.model.shared;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.SystemUtils;

@Getter
@ToString
public enum Platform {
    MAC,

    WINDOWS,

    LINUX,

    UNKNOWN;

    public static final Platform platform = currentPlatform();

    public static final int cpuCore = Runtime.getRuntime().availableProcessors();

    private static Platform currentPlatform() {
        if (SystemUtils.IS_OS_MAC) return Platform.MAC;

        if (SystemUtils.IS_OS_LINUX) return Platform.LINUX;

        if (SystemUtils.IS_OS_WINDOWS) return Platform.WINDOWS;

        return Platform.UNKNOWN;
    }
}
