package org.visual.model.ui.bar;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static org.visual.model.shared.SystemUtil.detect;


public class SystemTitleBar extends HBox {
    private final CommonTitleBar titleBar;
    {
        switch (detect()) {
            case MAC:
                titleBar = new MacOSTitleBar();
                break;
            case LINUX:
                titleBar = new LinuxTitleBar();
                break;
            case WINDOWS:
                titleBar = new WindowsTitleBar();
                break;
            case UNKNOWN:
            default:
                throw new UnsupportedOperationException("NOT SUPPORT PLATFORM");
        }

        getStylesheets().add("/system.css");
        getStyleClass().add("title-rounded");
        setAlignment(Pos.BASELINE_CENTER);
        setHgrow(titleBar, Priority.ALWAYS);
        getChildren().add(titleBar);
    }
}
