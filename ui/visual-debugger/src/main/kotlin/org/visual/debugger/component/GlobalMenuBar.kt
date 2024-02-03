package org.visual.debugger.component

import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.CheckMenuItem
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCombination
import javafx.stage.WindowEvent
import org.visual.component.display.SystemMenuBar

class GlobalMenuBar @JvmOverloads constructor(var exitHandle: EventHandler<ActionEvent>? = null) :
    SystemMenuBar() {
  private val exitItem by lazy {
    MenuItem("Exit").apply {
      accelerator = KeyCombination.keyCombination("CTRL+Q")
      onAction = exitHandle
    }
  }

  private val fileMenu by lazy { Menu("File").apply { items.add(exitItem) } }

  private val showBoundsCheckbox by lazy {
    val menuItem = CheckMenuItem("Show Bounds Overlays")
    menuItem.selectedProperty().addListener {
        _: ObservableValue<out Boolean>?,
        _: Boolean?,
        _: Boolean ->
      //                setStatusText(
      //                    if (newValue) toolTipSelected else toolTipNotSelected,
      //                    4000
      //                )
    }
    menuItem.id = "show-bounds-checkbox"
  }

  private val collapseControls by lazy {
    val menuItem = CheckMenuItem("Collapse controls In Tree")
    menuItem.selectedProperty().addListener {
        arg0: ObservableValue<out Boolean>?,
        arg1: Boolean?,
        newValue: Boolean ->
      //                setStatusText(
      //                    if (newValue) toolTipSelected else toolTipNotSelected,
      //                    4000
      //                )
    }
    menuItem.id = "collapseControls"
  }

  private val aboutItem by lazy {
    MenuItem("About").apply {
      onAction = EventHandler { event: ActionEvent? ->
        //            AboutBox.make(
        //                "About",
        //                null
        //            )
      }
    }
  }

  private val aboutMenu: Menu by lazy { Menu("Help").apply { items.add(aboutItem) } }

  init {
    id = "global-menubar"
    addEventHandler(WindowEvent.WINDOW_SHOWN) { prefWidthProperty().bind(scene.widthProperty()) }
    menus.addAll(fileMenu, aboutMenu)
  }
}
