package org.visual.component.container

import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.SplitPane
import java.io.File

class SplitAnimatePane : SplitPane() {
    private val _collapse = SimpleBooleanProperty(false)

    var collapse: Boolean?
        get() = _collapse.get()
        set(value) {
            value?.let {
                _collapse.set(value)
            }
        }
    fun toggle() {
        _collapse.set(_collapse.value)
    }
}
