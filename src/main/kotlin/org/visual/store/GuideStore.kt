package org.visual.store

import java.nio.file.Path
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import kotlin.io.path.Path
import org.apache.commons.lang3.SystemUtils

object GuideStore {

  val path by lazy { SimpleObjectProperty(SystemUtils.USER_HOME) }

  val projectName by lazy { SimpleStringProperty() }

  fun toJdkPath(): Path {
    return Path(path.get())
  }
}
