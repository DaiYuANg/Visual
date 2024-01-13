package org.visual.model.ui.bar

import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem

class SystemMenuBar : MenuBar() {
    init {
        this.isUseSystemMenuBar = true
        val menu = Menu("test")
        val it = MenuItem()
        it.text = "test"
        menu.items.add(it)
        menus.add(menu)
    }
}
