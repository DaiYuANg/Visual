package org.visual.model.component.router

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap

class RouterContext {
    private val routers = Object2ObjectArrayMap<String, Router>()

    fun go() {
        routers["test"] = Router()
    }
}
