package org.visual.model.debugger.context

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ChangeListener

object LayoutContext {
    private val _collapseSplitPane = SimpleBooleanProperty(false)
    private var collapseSplitPane
        get() = _collapseSplitPane.get()
        set(value) = _collapseSplitPane.set(value)

    fun toggleCollapse() {
        _collapseSplitPane.set(!_collapseSplitPane.get())
    }

    fun addCollapseListener(listener: ChangeListener<Boolean>){
        _collapseSplitPane.addListener(listener)
    }
}