package org.visual.model.ui.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;
import org.visual.model.ui.constst.OperationSystem;

@UtilityClass
public class SystemUtil {

    public static OperationSystem detect() {
        if (SystemUtils.IS_OS_LINUX) return OperationSystem.LINUX;

        if (SystemUtils.IS_OS_WINDOWS) return OperationSystem.LINUX;

        if (SystemUtils.IS_OS_MAC) return OperationSystem.MAC;

        return OperationSystem.UNKNOWN;
    }
}
