package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import javafx.scene.web.WebView;
import org.visual.model.i18n.core.I18n;
import org.visual.model.i18n.core.I18nUtil;

@Factory
public class UIFactory {
    @Bean
    WebView webView() {
        return new WebView();
    }

//    @Bean
//    StatusBar statusBar() {
//        return new StatusBar();
//    }

    @Bean
    I18n i18n() {
        return I18nUtil.getDefaultLocale();
    }
}
