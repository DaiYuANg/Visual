package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Singleton
import java.nio.file.Path
import java.util.function.Consumer
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.stage.DirectoryChooser
import lombok.extern.slf4j.Slf4j
import org.apache.commons.lang3.SystemUtils
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.visual.component.control.FontAwesomeSolidButton

@Singleton
@Slf4j
class GuideContent : HBox() {

  private val log = KotlinLogging.logger {}

  private val internalPath = SimpleStringProperty(SystemUtils.USER_HOME)

  private val dc by lazy {
    DirectoryChooser().apply { initialDirectory = Path.of(internalPath.get()).toFile() }
  }

  init {
    centerShapeProperty().set(true)
  }

  init {
    val label = Label("Path")
    val input = TextField().apply { textProperty().bind(internalPath) }
    val btn =
        FontAwesomeSolidButton(FontAwesomeSolid.FILE).apply {
          setOnAction {
            val path = dc.showDialog(scene.window)
            log.atTrace { message = "dir is :${path.absolutePath}" }
            internalPath.set(path.absolutePath)
          }
        }
    children.addAll(label, input, btn)
  }

  fun listenChoose(callback: Consumer<Path>) {
    internalPath.addListener { _, _, t2 -> run { callback.accept(Path.of(t2)) } }
  }
}
