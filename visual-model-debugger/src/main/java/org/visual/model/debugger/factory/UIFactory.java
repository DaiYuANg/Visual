package org.visual.model.debugger.factory;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.avaje.inject.PostConstruct;
import javafx.application.Application;
import javafx.scene.control.SplitPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import lombok.val;
import org.visual.model.component.theme.OsThemeDetector;
import org.visual.model.component.util.ScreenUtil;
import org.visual.model.debugger.component.SelectVirtualMachineDialog;
import org.visual.model.debugger.view.ScenicViewGui;
import org.visual.model.i18n.core.I18n;
import org.visual.model.i18n.core.I18nUtil;

@Factory
public class UIFactory {

    @PostConstruct
    void initUI(){
        val theme = OsThemeDetector.getDetector().isDark()
                ? new PrimerDark().getUserAgentStylesheet()
                : new PrimerLight().getUserAgentStylesheet();
        Application.setUserAgentStylesheet(theme);
    }

    @Bean
    SplitPane splitPane() {
        return new SplitPane();
    }

    @Bean
    WebView webView() {
        return new WebView();
    }

    @Bean
    I18n i18n() {
        return I18nUtil.getDefaultLocale();
    }

    @Bean
    Stage rootStage() {
        val stage = new Stage();
        val size = ScreenUtil.percentOfScreen(0.7);
        stage.setWidth(size.getLeft());
        stage.setHeight(size.getRight());
        stage.setTitle("Visual Model Debugger" + ScenicViewGui.VERSION);
        return stage;
    }

    @Bean
    SelectVirtualMachineDialog selectVirtualMachineDialog() {
        return new SelectVirtualMachineDialog();
    }
}
