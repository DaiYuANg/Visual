package org.visual.component.display

import javafx.scene.layout.Pane
import org.visual.shared.OS

class SystemTitleBar : Pane() {
  init {
    val titleBar =
        when (OS.OS) {
          OS.MAC -> MacOSTitleBar()
          OS.LINUX -> LinuxTitleBar()
          OS.WINDOWS -> WindowsTitleBar()
          else -> throw UnsupportedOperationException("NOT SUPPORT PLATFORM")
        }

    //    stylesheets.add("/system.css")
    styleClass.add("title-rounded")
    height = 50.0
    children.add(titleBar)
  }
}
