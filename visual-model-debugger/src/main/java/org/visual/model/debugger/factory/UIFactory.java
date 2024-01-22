package org.visual.model.debugger.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.DepthTest;
import javafx.scene.control.SplitPane;
import javafx.scene.web.WebView;
import lombok.val;
import org.visual.model.debugger.constant.PreferencesKey;
import org.visual.model.debugger.model.Persistence;
import org.visual.model.i18n.core.I18n;
import org.visual.model.i18n.core.I18nUtil;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

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

    @Bean
    SplitPane splitPane(Preferences preferences) {
        val sp = new SplitPane();
        sp.getDividers().addListener((ListChangeListener<SplitPane.Divider>) change ->
                sp.getDividers().getFirst().positionProperty()
                        .addListener((observableValue, number, t1) -> {
                            preferences.putDouble(PreferencesKey.SPLIT_DIVIER.getValue(), number.doubleValue());
                            try {
                                preferences.flush();
                            } catch (BackingStoreException e) {
                                throw new RuntimeException(e);
                            }
                        }));
        return sp;
    }
}
