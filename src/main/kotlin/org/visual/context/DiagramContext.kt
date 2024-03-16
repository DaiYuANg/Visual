package org.visual.context

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*
import javafx.collections.FXCollections
import javafx.collections.MapChangeListener
import org.visual.model.Diagram

object DiagramContext {

  private val log = KotlinLogging.logger {}

  private val _diagrams by lazy {
    val hashMap = FXCollections.observableHashMap<UUID, Diagram>()
    FXCollections.synchronizedObservableMap(hashMap)
  }

  init {
    _diagrams.addListener(
        MapChangeListener { log.atTrace { message = "Add diagram:${it.valueAdded}" } })
  }

  fun newDiagram(projectId: UUID) {
    _diagrams[projectId] = Diagram.builder().projectId(projectId).build()
  }
}
