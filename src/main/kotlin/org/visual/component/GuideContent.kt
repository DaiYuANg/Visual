package org.visual.component

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.inject.Singleton
import java.nio.file.Path
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.stage.DirectoryChooser
import lombok.extern.slf4j.Slf4j
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid
import org.visual.component.control.FontAwesomeSolidButton
import org.visual.store.GuideStore

@Singleton
@Slf4j
class GuideContent : GridPane() {

  private val log = KotlinLogging.logger {}

  private val dc by lazy {
    DirectoryChooser().apply { initialDirectory = Path.of(GuideStore.path.get()).toFile() }
  }

  init {
    centerShapeProperty().set(true)
    alignment = Pos.CENTER
    hgap = 10.0
    vgap = 10.0
    padding = Insets(25.0, 25.0, 25.0, 25.0)
  }

  init {
    val sceneTitle = Text("Create Visual Project")
    add(sceneTitle, 0, 0, 2, 1)

    val total = Label("Name:")
    add(total, 0, 1)

    val totalField = TextField().apply { textProperty().bindBidirectional(GuideStore.projectName) }
    add(totalField, 1, 1)

    val percent = Label("Path")
    add(percent, 0, 2)

    val inputBox =
        HBox().apply {
          val pathInput: TextField = TextField().apply { textProperty().bind(GuideStore.path) }
          val btn =
              FontAwesomeSolidButton(FontAwesomeSolid.FILE).apply {
                setOnAction {
                  val path = dc.showDialog(scene.window)
                  log.atTrace { message = "dir is :${path.absolutePath}" }
                  GuideStore.path.set(path.absolutePath)
                }
              }
          alignment = Pos.CENTER
          children.addAll(pathInput, btn)
        }

    add(inputBox, 1, 2)
  }
}
