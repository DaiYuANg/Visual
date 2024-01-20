/* (C)2024*/
package org.visual.model.app.core;

import io.avaje.inject.BeanScope;
import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Getter
public enum VisualModelAppContext {
    INSTANCE;

    private final BeanScope scope = BeanScope.builder().build();

    public <T> @NotNull T get(Class<T> clazz) {
        return scope.get(clazz);
    }

    @SneakyThrows
    public Parent load(String prefix) {
        val loader = new FXMLLoader(VisualModelUI.class.getResource(prefix + ".fxml"));
        loader.setControllerFactory(scope::get);
        loader.setCharset(StandardCharsets.UTF_8);
        return loader.load();
    }
}
