package org.visual.app.component

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.scene.control.Button
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.kordamp.ikonli.javafx.FontIcon

class IconButton : Button() {
  private val logger = KotlinLogging.logger {}

  init {
    graphic = FontIcon(FontAwesomeSolid.ICONS)
  }

  init {
    logger.atInfo { message = "IconButton" }
  }
}