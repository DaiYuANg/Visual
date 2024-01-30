package org.visual.framework

import io.avaje.inject.BeanScope

class VisualFrameworkContext(
    vararg scopeChildren:BeanScope
) {
    private val rootBeanScope by lazy {
        BeanScope.builder()
            .shutdownHook(true)
            .build()
    }
}