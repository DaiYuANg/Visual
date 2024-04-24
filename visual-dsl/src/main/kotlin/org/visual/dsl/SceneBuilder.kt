package org.visual.dsl

import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.Pane

class SceneBuilder {
  fun scene(init: SceneScope.() -> Unit): Scene {
    val scope = SceneScope()
    scope.init()
    return scope.createScene()
  }

  inner class SceneScope {
    private val root = Pane()
    private val components = mutableListOf<Node>()
    private var width = SimpleDoubleProperty(800.0)
    private var height = SimpleDoubleProperty(600.0)

    fun createScene(): Scene {
      root.children.addAll(components)
      return Scene(root, width.value, height.value)
    }

    fun width(value: Number) {
      width.value = value.toDouble()
    }

    fun height(value: Number) {
      height.value = value.toDouble()
    }

    fun vbox(spacing: Double? = null, padding: Insets? = null, init: VBoxBuilder.() -> Unit) {
      val vbox =
          VBoxBuilder().apply {
            this.spacing = spacing
            this.padding = padding
            init()
          }
      components.add(vbox.build())
    }

    fun button(text: String, init: ButtonBuilder.() -> Unit) {
      val button = ButtonBuilder(text).apply(init).build()
      components.add(button)
    }
  }
}
