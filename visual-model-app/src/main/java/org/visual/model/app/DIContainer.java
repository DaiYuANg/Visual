package org.visual.model.app;

import io.avaje.inject.BeanScope;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@Getter
public enum DIContainer {
    INSTANCE;

    private final BeanScope scope = BeanScope.builder().build();

    public <T> @NotNull T get(Class<T> clazz) {
        return scope.get(clazz);
    }

    @SneakyThrows
    public Parent load(String prefix) {
        val loader = new FXMLLoader(VisualModelApplication.class.getResource(prefix + ".fxml"));
        loader.setControllerFactory(scope::get);
        loader.setCharset(StandardCharsets.UTF_8);
        return loader.load();
    }
}
