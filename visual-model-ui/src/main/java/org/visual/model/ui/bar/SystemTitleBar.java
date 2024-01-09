package org.visual.model.ui.bar;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import lombok.Getter;
import lombok.ToString;
import org.visual.model.ui.util.SystemUtil;

@Getter
@ToString
public class SystemTitleBar extends HBox {

    private final HBox rootBox = new HBox();

    private final TitleBar titleBar;

    {
        rootBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public SystemTitleBar() {
        titleBar = switch (SystemUtil.detect()) {
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
