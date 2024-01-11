package org.visual.model.ui.bar;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;
import lombok.ToString;
import org.visual.model.ui.TitleBar;

import static org.visual.model.shared.SystemKt.detect;


@Getter
@ToString
public class SystemTitleBar extends HBox {

    private final HBox rootBox = new HBox();

    private final org.visual.model.ui.TitleBar titleBar;

    {
        rootBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public SystemTitleBar() {
        titleBar = switch (detect()) {
            case MAC -> new MacosTitleBar();
            case LINUX -> new LinuxTitleBar();
            case WINDOWS -> new WindowsTitleBar();
            default -> new TitleBar();
        };
    }

    private void initialize() {
        rootBox.getChildren().add(titleBar);
    }
}
