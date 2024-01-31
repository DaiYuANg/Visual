package org.visual.debugger.module

import atlantafx.base.theme.PrimerDark
import atlantafx.base.theme.PrimerLight
import com.google.inject.AbstractModule
import com.google.inject.Provides
import javafx.application.Application
import javafx.scene.control.ContextMenu
import javafx.scene.control.SplitPane
import javafx.scene.web.WebView
import javafx.stage.Stage
import org.visual.component.theme.OsThemeDetector
import org.visual.component.util.ScreenUtil
import org.visual.debugger.view.ScenicViewGui
import org.visual.i18n.I18n
import org.visual.i18n.I18nUtil

class UIFactory:AbstractModule(){
    init {
        val theme =
            if (OsThemeDetector.getDetector().isDark
            ) PrimerDark().userAgentStylesheet
            else PrimerLight().userAgentStylesheet
        Application.setUserAgentStylesheet(theme)
    }

    @Provides
    fun splitPane(): SplitPane {
        return SplitPane()
    }

    @Provides
    fun webView(): WebView {
        return WebView()
    }

    @Provides
    fun i18n(): I18n {
        return I18nUtil.getDefaultLocale()
    }

    @Provides
    fun rootStage(): Stage {
        val stage = Stage()
        val size = ScreenUtil.percentOfScreen(0.7)
        stage.width = size.left
        stage.height = size.right
        stage.title = "Visual Model Debugger" + ScenicViewGui.VERSION
        return stage
    }

    @Provides
    fun contextMenu(): ContextMenu {
        return ContextMenu()
    }
}
