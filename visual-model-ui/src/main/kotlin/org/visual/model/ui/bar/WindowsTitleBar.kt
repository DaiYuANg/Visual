package org.visual.model.ui.bar

import org.kordamp.ikonli.fluentui.FluentUiFilledAL
import org.kordamp.ikonli.fluentui.FluentUiFilledMZ
import org.kordamp.ikonli.javafx.FontIcon
import org.visual.model.ui.control.IconButton


class WindowsTitleBar internal constructor() : CommonTitleBar() {
    private val closeButton = IconButton(FluentUiFilledAL.DISMISS_24)

    private val minimizeButton = IconButton(FluentUiFilledMZ.MINIMIZE_24)

    private val maximizeButton = IconButton(FluentUiFilledMZ.MAXIMIZE_24)

    private val frontendIcon = FontIcon(FluentUiFilledMZ.MAXIMIZE_16)
    private val behind = FontIcon(FluentUiFilledMZ.MAXIMIZE_16)


    init {

    }
}
