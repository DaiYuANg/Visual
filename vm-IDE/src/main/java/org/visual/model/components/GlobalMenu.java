package org.visual.model.components;

import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class GlobalMenu {
    @Getter
    private final Parent rootMenuBar;

    @SneakyThrows
    public GlobalMenu() {
        rootMenuBar = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("global-menu.fxml")));
    }
}
