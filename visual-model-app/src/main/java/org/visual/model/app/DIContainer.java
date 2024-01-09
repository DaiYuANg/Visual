package org.visual.model.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.visual.model.app.module.RootModule;
import org.visual.model.app.module.UIModule;

import java.nio.charset.StandardCharsets;

public enum DIContainer {

    INSTANCE;

    private final RootModule rootModule = new RootModule();

    private final UIModule uiModule = new UIModule();

    @Getter
    private final Injector injector = Guice.createInjector(rootModule, uiModule);

    @SneakyThrows
    public Parent load(String prefix) {
        val loader = new FXMLLoader(VisualModelUI.class.getResource(prefix + ".fxml"));
        loader.setControllerFactory(DIContainer.INSTANCE.injector::getInstance);
        loader.setCharset(StandardCharsets.UTF_8);
        return loader.load();
    }
}
