package org.visual.view

import jakarta.inject.Inject
import jakarta.inject.Singleton
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import org.visual.component.SearchPane
import org.visual.context.DIContext

@Singleton
class ShortcutRegistry @Inject constructor(scene: Scene) {

  init {
    val saveCombination = KeyCodeCombination(KeyCode.F, KeyCombination.META_DOWN)
    scene.addEventFilter(KeyEvent.KEY_PRESSED) {
      if (saveCombination.match(it)) {
        val pane = DIContext.INSTANCE.get(SearchPane::class.java)
        when {
          pane.isShowing -> {
            pane.toFront()
          }
          else -> {
            pane.show()
          }
        }
        it.consume()
      }
    }
  }
}
