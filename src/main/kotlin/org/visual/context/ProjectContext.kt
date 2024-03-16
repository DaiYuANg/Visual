package org.visual.context

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*
import java.util.function.Consumer
import javafx.collections.FXCollections
import javafx.collections.MapChangeListener
import org.visual.model.Project

object ProjectContext {

  private val log = KotlinLogging.logger {}
  private val _projects = run {
    val hashMap = FXCollections.observableHashMap<UUID, Project>()
    FXCollections.synchronizedObservableMap(hashMap)
  }

  init {
    _projects.addListener(
        MapChangeListener { log.atTrace { message = "Project add:${it.valueAdded}" } })
  }

  fun getProject(id: UUID): Project? {
    return _projects[id]
  }

  fun listen(callback: Consumer<Project>) {
    _projects.addListener(MapChangeListener { callback.accept(it.valueAdded) })
  }

  fun createProject(project: Project) {
    _projects[project.uuid] = project
  }
}
