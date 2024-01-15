package org.visual.model.shared;

import org.apache.commons.lang3.SystemUtils;

public class SystemUtil {
    public static OperationSystem detect() {

        if (SystemUtils.IS_OS_MAC) return OperationSystem.MAC;

        if (SystemUtils.IS_OS_LINUX) return OperationSystem.LINUX;

        if (SystemUtils.IS_OS_WINDOWS) return OperationSystem.WINDOWS;

        return OperationSystem.UNKNOWN;
    }
}
