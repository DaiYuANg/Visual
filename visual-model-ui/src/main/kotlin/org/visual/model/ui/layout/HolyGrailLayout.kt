package org.visual.model.ui.layout

import javafx.geometry.Orientation
import javafx.scene.Node
import javafx.scene.control.Menu
import javafx.scene.control.SplitPane
import javafx.scene.control.ToolBar
import javafx.scene.layout.Border
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color

class HolyGrailLayout : VBox() {

    private val styleFile = "/split.css"

    private val hSplitPane by lazy {
        SplitPane().apply {
            this.orientation = Orientation.HORIZONTAL
            setFocused(true)
            setVgrow(this@HolyGrailLayout, Priority.ALWAYS)
            setDividerPositions(0.2, 0.9, 0.1)
        }
    }

    private val vSplitPane by lazy {
        SplitPane().apply {
            this.orientation = Orientation.VERTICAL
            setDividerPositions(0.9, 0.05)
        }
    }

    private val footer by lazy {
        HBox()
    }

    private val header by lazy {
        HBox().apply {
            children.add(ToolBar())
        }
    }

    init {
        setVgrow(this, Priority.ALWAYS)
        vSplitPane.border = Border.stroke(Color.GRAY)
        vSplitPane.items.addAll(hSplitPane, footer)
        vSplitPane.lookupAll(".split-pane-divider").forEach {
            it.style = "-fx-background-color: red;" // 设置分隔条的背景颜色
        }
        setVgrow(vSplitPane, Priority.ALWAYS)
        children.addAll(header, vSplitPane)
        stylesheets.add(styleFile)
    }

    fun addItem(vararg e: Node) {
        hSplitPane.items.addAll(*e)
    }
}