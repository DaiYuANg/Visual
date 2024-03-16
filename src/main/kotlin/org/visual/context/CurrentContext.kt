package org.visual.context

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*
import java.util.function.Consumer
import javafx.beans.InvalidationListener
import javafx.beans.property.SimpleObjectProperty

object CurrentContext {

  private val log = KotlinLogging.logger {}

  private val _current = SimpleObjectProperty<UUID>()

  var current: UUID?
    get() {
      return _current.get()
    }
    set(value) {
      _current.set(value)
    }

  init {
    _current.addListener(InvalidationListener { log.atTrace { message = "Current Project:${it}" } })
  }

  fun listen(callback: Consumer<UUID>) {
    _current.addListener { _, _, newValue -> run { callback.accept(newValue) } }
  }
}
