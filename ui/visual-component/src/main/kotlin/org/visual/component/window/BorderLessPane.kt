package org.visual.component.window

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Cursor
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import org.visual.component.extension.*

class BorderLessPane(
    private val stage: Stage,
) : AnchorPane() {
  private val topLeftPane by lazy {
    pane {
      setTopLeftAnchor(this)
      cursor(Cursor.NW_RESIZE)
      prefSize()
    }
  }

  private val topRightPane by lazy {
    pane {
      prefSize()
      cursor(Cursor.NE_RESIZE)
      setTopRightAnchor(this)
    }
  }

  private val bottomRightPane by lazy {
    pane {
      prefSize()
      cursor(Cursor.SE_RESIZE)
      setBottomRightAnchor(this)
    }
  }

  private val bottomLeftPane by lazy {
    pane {
      prefSize()
      cursor(Cursor.W_RESIZE)
      setBottomLeftAnchor(this)
    }
  }

  private val maximized: SimpleBooleanProperty by lazy { SimpleBooleanProperty(false) }

  private val resizable: SimpleBooleanProperty by lazy { SimpleBooleanProperty(true) }

  private val snap: SimpleBooleanProperty by lazy { SimpleBooleanProperty(true) }

  private val snapped = false
}
