@file:JvmName("VisualModelEditor")
package org.visual.model.ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.slf4j.LoggerFactory

class VisualModelEditor : Application() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun init() {
        log.info("virtual")
    }

    override fun start(p0: Stage?) {
//        FXMLLoader().load<Parent>()
        val root = Pane()
//        val sb = SystemManuBar()
//        val menu = Menu()
//        val item = MenuItem()
//        item.text = "test"
//        menu.items.add(item)
//        sb.menus.add(menu)
//        root.children.add(sb)
        p0?.apply {
            title = "VisualModel"
            scene = (Scene(root, width, height))
            show()
        }
    }
}

