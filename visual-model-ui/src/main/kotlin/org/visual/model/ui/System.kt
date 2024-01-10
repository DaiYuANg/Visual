package org.visual.model.ui

import org.apache.commons.lang3.SystemUtils
import org.apache.commons.lang3.SystemUtils.*

fun detect(): OperationSystem {
    return when {
        IS_OS_LINUX -> OperationSystem.LINUX
        IS_OS_WINDOWS -> OperationSystem.WINDOWS
        IS_OS_MAC -> OperationSystem.MAC
        else -> OperationSystem.UNKNOWN
    }
}