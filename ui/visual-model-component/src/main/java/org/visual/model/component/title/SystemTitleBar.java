package org.visual.model.component.title;

import static org.visual.model.shared.Platform.platform;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.visual.model.component.CommonTitleBar;


public class SystemTitleBar extends HBox {
    {
        CommonTitleBar titleBar = switch (platform) {
            case MAC -> new MacOSTitleBar();
            case LINUX -> new LinuxTitleBar();
            case WINDOWS -> new WindowsTitleBar();
            default -> throw new UnsupportedOperationException("NOT SUPPORT PLATFORM");
        };

        getStylesheets().add("/system.css");
        getStyleClass().add("title-rounded");
        setAlignment(Pos.BASELINE_CENTER);
        setHgrow(titleBar, Priority.ALWAYS);
        getChildren().add(titleBar);
    }
}