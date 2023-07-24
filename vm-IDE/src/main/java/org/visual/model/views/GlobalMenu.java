package org.visual.model.views;

import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public enum GlobalMenu {
    GLOBAL_MENU;
    @Getter
    private final Parent rootMenuBar;

    @SneakyThrows
    GlobalMenu() {
        rootMenuBar = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("global-menu.fxml")));
    }
}
