package org.visual.debugger.context

import javafx.collections.FXCollections
import javafx.collections.MapChangeListener
import javafx.collections.ObservableMap
import javafx.scene.Scene
import org.visual.shared.KSlf4j
import org.visual.shared.KSlf4j.Companion.log

@KSlf4j
object AttachSceneContext {
    val scene: ObservableMap<Long, Scene> by lazy {
        FXCollections.observableHashMap()
    }

    init {
        scene.addListener(MapChangeListener {
            log.atInfo {
                "add scene ${it.key} scenes:${scene}"
            }
        })
    }
}