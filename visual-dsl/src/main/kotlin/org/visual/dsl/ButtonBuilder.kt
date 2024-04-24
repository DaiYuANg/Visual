package org.visual.dsl

import javafx.scene.control.Button

class ButtonBuilder(private val text: String) {
  private var onActionHandler: (() -> Unit)? = null

  fun build(): Button {
    val button = Button(text)
    onActionHandler?.let { button.setOnAction { it() } }
    return button
  }

  fun onAction(block: () -> Unit) {
    onActionHandler = block
  }
}
