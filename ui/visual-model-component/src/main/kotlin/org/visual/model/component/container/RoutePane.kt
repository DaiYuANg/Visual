package org.visual.model.component.container

import java.util.concurrent.ConcurrentHashMap
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import org.visual.model.component.annotation.FxComponent

@FxComponent
class RoutePane : StackPane() {
  private val routers by lazy { ConcurrentHashMap<String, Int>() }

  fun addRoute(path: String, pane: Pane) {
    val index = if (children.isEmpty()) 0 else -1
    children.add(index, pane)
    routers[path] = index
  }
}
