package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Singleton
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.layout.GridPane
import lombok.extern.slf4j.Slf4j
import org.visual.feature.AvailableFeature

@Singleton
@Slf4j
class GuideContent : GridPane() {

  private val log = KotlinLogging.logger {}

  private val featureProperty = SimpleObjectProperty<AvailableFeature>()

  init {
    prefWidth = 500.0
    prefHeight = 500.0
  }

  init {
    val all = AvailableFeature.entries.stream().map { it.thumbnail }.toList()
    addRow(0, *all.toTypedArray())
  }
}
