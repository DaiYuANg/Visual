package org.visual.model.ui.bar

import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import lombok.Getter
import lombok.ToString
import org.visual.model.shared.OperationSystem
import org.visual.model.shared.OperationSystem.*
import org.visual.model.shared.detect

@Getter
@ToString
class SystemTitleBar : HBox() {
    private val titleBar = when (detect()) {
        MAC -> MacosTitleBar()
        LINUX -> LinuxTitleBar()
        WINDOWS -> WindowsTitleBar()
        UNKNOWN -> TODO("NOT SUPPORT PLATFORM")
    }

    init {
        stylesheets.add("/system.css")
        styleClass.add("title-rounded")
        alignment = Pos.BASELINE_CENTER
        setHgrow(titleBar, Priority.ALWAYS)
        children.add(titleBar)
    }
}
