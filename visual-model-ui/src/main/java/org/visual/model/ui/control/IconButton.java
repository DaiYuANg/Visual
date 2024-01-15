package org.visual.model.ui.control;

import javafx.scene.control.Button;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

public class IconButton extends Button {

    private final FontIcon fontIcon;

    public IconButton(Ikon icon) {
        fontIcon = new FontIcon(icon);
        fontIcon.setIconSize(20);
        setGraphic(fontIcon);
    }
}
