package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import javafx.scene.web.WebView;

@Factory
public class UIFactory {
    @Bean
    WebView webView() {
        return new WebView();
    }
}
