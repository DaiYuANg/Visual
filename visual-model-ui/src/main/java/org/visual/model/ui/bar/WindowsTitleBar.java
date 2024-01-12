package org.visual.model.ui.bar;


import lombok.val;
import org.kordamp.ikonli.fluentui.FluentUiFilledAL;
import org.kordamp.ikonli.fluentui.FluentUiFilledMZ;
import org.kordamp.ikonli.javafx.FontIcon;
import org.visual.model.ui.TitleBar;
import org.visual.model.ui.control.IconButton;

public class WindowsTitleBar extends TitleBar {

    private final IconButton closeButton = new IconButton(FluentUiFilledAL.DISMISS_24);

    private final IconButton minimizeButton = new IconButton(FluentUiFilledMZ.MINIMIZE_24);

    private final IconButton maximizeButton = new IconButton(FluentUiFilledMZ.MAXIMIZE_24);

    static {
        val frontendIcon = new FontIcon(FluentUiFilledMZ.MAXIMIZE_16);
        val behind = new FontIcon(FluentUiFilledMZ.MAXIMIZE_16);
    }

    WindowsTitleBar() {
    }
}
