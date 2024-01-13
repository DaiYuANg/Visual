package org.visual.model.app.controller

import jakarta.inject.Inject
import javafx.fxml.FXML
import javafx.fxml.Initializable
import org.github.gestalt.config.Gestalt
import org.visual.model.shared.KSlf4j
import org.visual.model.shared.KSlf4j.Companion.log
import org.visual.model.ui.bar.CommonTitleBar
import org.visual.model.ui.bar.SystemTitleBar
import java.lang.Thread.sleep
import java.net.URL
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@KSlf4j
class MainLayoutController : Initializable {

    @FXML
    private lateinit var titleBar: SystemTitleBar
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
    }
}
