package org.visual.model.ui.widget

import javafx.application.Platform
import javafx.stage.Stage
import java.awt.*
import java.awt.event.ActionListener
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolutePathString


class Tray @JvmOverloads constructor(
    iconPath: Path = Paths.get(""),
    primaryStage: Stage,
    menuItems: Collection<MenuItem> = listOf(),
) {
    private val tray: SystemTray = SystemTray.getSystemTray()
    private val image: Image = Toolkit.getDefaultToolkit().getImage(iconPath.absolutePathString())
    private val popup: PopupMenu = PopupMenu()
        .apply {
            menuItems.forEach {
                add(it)
            }
        }

    private val trayListener: ActionListener = ActionListener {
        if (primaryStage.isShowing) Platform.runLater { primaryStage.hide() } else Platform.runLater { primaryStage.show() }
    }

    // construct a TrayIcon
    private val trayIcon = TrayIcon(image, "Tray Demo", popup).apply {
        addActionListener(trayListener)
    }

    init {
        Platform.setImplicitExit(false);
    }

    init {
        tray.add(trayIcon)
    }
}