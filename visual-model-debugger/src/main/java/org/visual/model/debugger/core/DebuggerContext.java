package org.visual.model.debugger.core;

import io.avaje.inject.BeanScope;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Getter
public enum DebuggerContext {

    INSTANCE;

    private final BeanScope beanScope = BeanScope.builder()
            .shutdownHook(true)
            .build();

    DebuggerContext(){
        System.err.println(beanScope.all());
    }

    public <T> @NotNull T get(Class<T> clazz) {
        return beanScope.get(clazz);
    }

    @SneakyThrows
    public Parent load(String prefix) {
        val loader = new FXMLLoader(VisualModelDebugger.class.getResource(prefix + ".fxml"));
        loader.setControllerFactory(beanScope::get);
        loader.setCharset(StandardCharsets.UTF_8);
        return loader.load();
    }
}
