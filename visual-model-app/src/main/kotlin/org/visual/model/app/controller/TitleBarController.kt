package org.visual.model.app.controller

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import java.net.URL
import java.util.*

class TitleBarController : Initializable {
    lateinit var closeButton: Button
    override fun initialize(p0: URL?, p1: ResourceBundle?) {

    }

    @FXML
    fun close() {
        System.err.println("action")
    }
}